import React, { useState, useEffect } from "react";
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  Alert,
} from "react-native";
import { useGlobalSearchParams, useRouter } from "expo-router";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function ResultScreen() {
  const {
    license_plate_no,
    vehicleType,
    fuelType,
    availableFuelCapacity,
    vehicleId,
    userId,
  } = useGlobalSearchParams();

  const router = useRouter();
  const [stationId, setStationId] = useState();
  const [enteredFuelAmount, setEnteredFuelAmount] = useState("");
  const [telNo, setTelNo] = useState(0);

  const handleButtonPress = (value: string) => {
    setEnteredFuelAmount((prev) => prev + value);
  };

  const handleDelete = () => {
    setEnteredFuelAmount((prev) => prev.slice(0, -1));
  };

  const data = {
    stationId: stationId,
    fuelType: fuelType,
    fuelAmount: enteredFuelAmount,
  };

  useEffect(() => {
    const fetchStationId = async () => {
      try {
        const fuelId = await AsyncStorage.getItem("stationId");
        setStationId(fuelId);
      } catch (error) {
        console.error("Error fetching fuel station ID:", error);
      }
    };

    fetchStationId();
  }, []);

  const handleSubmit = async () => {
    if (enteredFuelAmount === "") {
      Alert.alert("Error", "Please enter the amount of fuel.");
      return;
    }
    if (Number(enteredFuelAmount) <=Number(availableFuelCapacity)){
    
    }else{
      Alert.alert("Error", "Not enough fuel capacity");
      return;
    }
    if (Number(enteredFuelAmount) <= Number(availableFuelCapacity)) {
        
      try {
        const response = await axios.post(
          `https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/fuelAllocation/add/${vehicleId}/${userId}`,
          data,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
 

        await axios.post(
          `https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/${vehicleId}/update-fuel`,
          enteredFuelAmount,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        const response4 = await axios.get(
          `https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/findfuel/${fuelType}`
        );
        const price = response4.data.fuelPriceDtoList[0].price;

        const Total = (Number(price) * Number(enteredFuelAmount)).toFixed(2);
        Alert.alert(
          "Fuel Submitted",
          `Amount: ${enteredFuelAmount}L\nCost:${Total}Rs`
        );
        router.back();
      } catch (error) {
        Alert.alert("Error", "Failed to submit fuel amount.");
      }

      try {
        const body = {
          fuelType: fuelType,
          quantity: enteredFuelAmount,
        };
        const stLicense = await AsyncStorage.getItem("fuelStationId");

        await axios.post(
          `https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/fuel/updatefuel/${stLicense}`,
          body
        ),
          {
            headers: {
              "Content-Type": "application/json",
            },
          };
      } catch (error) {}
    } else {
      Alert.alert("Error", "Not enough fuel capacity available.");
    }

    try {
      const stLicense = await AsyncStorage.getItem("stationId");
      const now = new Date();
      const formattedDateTime = now.toLocaleString();

      const response = await axios.post(
        "https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/notifications/generateNotifications",
        {},
        {
          params: {
            telno: "+94743159018", 

            message: `${enteredFuelAmount}L ${fuelType} pumped to ${license_plate_no} in station ${stLicense} at ${formattedDateTime}`,
          },
        }
      );

      if (response.data.statusCode === 200) {
        // console.log("Notification sent successfully:", response.data);
        // Alert.alert("Success", "Notification sent successfully!");
      } else {
        // console.log("Failed to send notification:", response.data);
        Alert.alert("Error", "Failed to send notification.");
      }
    } catch (error) {
      // console.error("Error sending notification:", error);
      Alert.alert("Error", "An error occurred while sending the notification.");
    }
  };

  const handleDotPress = () => {
    if (!enteredFuelAmount.includes(".")) {
      setEnteredFuelAmount((prev) => prev + ".");
    }
  };

  useEffect(() => {
    const fetchMobileNumber = async () => {
      try {
        const response = await axios.get(
          `https://43c8-2402-4000-13f3-d2b3-4dc9-9a03-4b48-a3f8.ngrok-free.app/api/account/${userId}`
        );
        setTelNo(response.data.userAccountDto.telno);
      } catch (error) {
        Alert.alert("Error", "Failed to fetch mobile number.");
      }
    };
    fetchMobileNumber();
  }, [userId]); // Ensure it re-runs if userId changes

  useEffect(() => {
    if (telNo) {
      console.log("Updated telNo:", telNo);
    }
  }, [telNo]);

  return (
    <View style={styles.container}>
      <View style={styles.containerDetails}>
        <View style={styles.col}>
          <Text style={styles.title}>Vehicle Details</Text>
          <Text style={styles.textLarge}>{license_plate_no}</Text>
        </View>

        <View style={styles.row}>
          <View style={styles.col}>
            <Text style={styles.lable}>Vehicle Type</Text>
            <Text style={styles.value}>{vehicleType}</Text>
          </View>

          <View style={styles.col}>
            <Text style={styles.lable}>Fuel Type</Text>
            <Text style={styles.value}>{fuelType}</Text>
          </View>
        </View>
        <View style={styles.col}>
          <Text style={styles.title}>Available Fuel Capacity</Text>
          <Text style={styles.textLarge}>{availableFuelCapacity}L</Text>
        </View>
      </View>

      <TextInput
        style={styles.inputBox}
        value={enteredFuelAmount}
        onChangeText={setEnteredFuelAmount}
        keyboardType="numeric"
        placeholder="Enter fuel amount"
        placeholderTextColor="rgba(0, 0, 0, 0.5)"
      />

      <View style={styles.buttonRow}>
        {[1, 2, 3].map((num) => (
          <TouchableOpacity
            key={num}
            style={styles.button}
            onPress={() => handleButtonPress(num.toString())}
          >
            <Text style={styles.buttonText}>{num}</Text>
          </TouchableOpacity>
        ))}
      </View>
      <View style={styles.buttonRow}>
        {[4, 5, 6].map((num) => (
          <TouchableOpacity
            key={num}
            style={styles.button}
            onPress={() => handleButtonPress(num.toString())}
          >
            <Text style={styles.buttonText}>{num}</Text>
          </TouchableOpacity>
        ))}
      </View>
      <View style={styles.buttonRow}>
        {[7, 8, 9].map((num) => (
          <TouchableOpacity
            key={num}
            style={styles.button}
            onPress={() => handleButtonPress(num.toString())}
          >
            <Text style={styles.buttonText}>{num}</Text>
          </TouchableOpacity>
        ))}
      </View>
      <View style={styles.buttonRow}>
        <TouchableOpacity
          style={styles.button}
          onPress={() => handleButtonPress("0")}
        >
          <Text style={styles.buttonText}>0</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={handleDotPress}>
          <Text style={styles.buttonText}>.</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.button, styles.deleteButton]}
          onPress={handleDelete}
        >
          <Text style={styles.buttonText}>DEL</Text>
        </TouchableOpacity>
      </View>

      <View style={styles.buttonRow}>
        <TouchableOpacity
          style={[styles.buttonn, styles.submitButton]}
          onPress={handleSubmit}
        >
          <Text style={styles.buttonText}>SUBMIT</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.buttonn, styles.cancelButton]}
          onPress={() => router.back()}
        >
          <Text style={styles.buttonText}>CANCEL</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    width: "100%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#f5f5f5",
  },
  containerDetails: {},
  col: {
    flexDirection: "column",
    alignItems: "center",
  },
  textLarge: {
    fontSize: 30,
    fontWeight: "bold",
    color: "black",
  },
  title: {
    fontSize: 20,
    fontWeight: "bold",
    marginBottom: 20,
  },
  inputBox: {
    height: 50,
    width: "80%",
    borderColor: "#ccc",
    borderWidth: 1,
    borderRadius: 8,
    fontSize: 18,
    paddingLeft: 10,
    marginVertical: 20,
  },
  buttonRow: {
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "center",
    marginBottom: 10,
  },
  button: {
    width: 60,
    height: 60,
    justifyContent: "center",
    alignItems: "center",
    margin: 5,
    backgroundColor: "#0E7AFE",
    borderRadius: 8,
  },
  buttonn: {
    width: 100,
    height: 60,
    margin: 5,
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 8,
  },
  deleteButton: {
    backgroundColor: "red",
  },
  submitButton: {
    backgroundColor: "green",
  },
  cancelButton: {
    backgroundColor: "orange",
  },
  buttonText: {
    fontSize: 24,
    color: "#fff",
  },
  row: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 40,
    marginTop: 30,
    gap: 50,
  },
  lable: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 10,
  },
  value: {
    fontSize: 16,
    fontWeight: "600",
  },
});
