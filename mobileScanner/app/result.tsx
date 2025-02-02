import React, { useState,useEffect} from "react";
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
import AsyncStorage from '@react-native-async-storage/async-storage';


export default function ResultScreen() {
    const {
      license_plate_no,
      vehicleType,
      fuelType,
      availableFuelCapacity,
      vehicleId,
      userId
    } = useGlobalSearchParams();

    const router = useRouter();
    const [stationId,setStationId] = useState();
    const [enteredFuelAmount, setEnteredFuelAmount] = useState("");

    const handleButtonPress = (value: string) => {
        setEnteredFuelAmount((prev) => prev + value);
      };

      const handleDelete = () => {
        setEnteredFuelAmount((prev) => prev.slice(0, -1));
      };

      const data={
        stationId:stationId,
        fuelType:fuelType,
        fuelAmount:enteredFuelAmount
      }

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


       //  const calculateCost=async(amount: string) => {
  //   const response= await axios.get(`https://888a-2402-4000-13cb-8706-5093-26da-1b8d-e09d.ngrok-free.app/allfuelPrices`);
  //   const selectedObject = response.data.fuelPriceDtoList.find((item) => item.fuelName.toLowerCase() === fuelType.toLowerCase());
  //   const price=selectedObject.price;
    
  //   return price * Number(amount);

  // }
  const handleSubmit = async () => {
    if (enteredFuelAmount === "") {
      Alert.alert("Error", "Please enter the amount of fuel.");
      return;
    }

    if(Number(enteredFuelAmount) <= Number(availableFuelCapacity)) {
        try {
          const response= await axios.post(`https://888a-2402-4000-13cb-8706-5093-26da-1b8d-e09d.ngrok-free.app/api/fuelAllocation/add/${vehicleId}/${userId}`,
            data,
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
          );


          
        await axios.post(
            `https://888a-2402-4000-13cb-8706-5093-26da-1b8d-e09d.ngrok-free.app/api/${vehicleId}/update-fuel`,
            enteredFuelAmount,
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          const cost = 10;
          Alert.alert("Fuel Submitted", `Amount: ${enteredFuelAmount}L\nCost:${cost}`);
          router.back(); 
        } catch (error) {
          Alert.alert("Error", "Failed to submit fuel amount.");
        }
      
        try{
          const body = {
            fuelType: fuelType,
            quantity: enteredFuelAmount
          };
           const stLicense= await AsyncStorage.getItem('fuelStationId');
           
           await axios.post(`https://888a-2402-4000-13cb-8706-5093-26da-1b8d-e09d.ngrok-free.app/api/fuel/updatefuel/${stLicense}`,body),
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
        }catch(error){
                
        }
      
      
      
      
      }
      
      
      else {
        Alert.alert("Error", "Not enough fuel capacity available.");
      }
    };
  
    const handleDotPress = () => {
      if (!enteredFuelAmount.includes(".")) {
        setEnteredFuelAmount((prev) => prev + ".");
      }
    };
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
      