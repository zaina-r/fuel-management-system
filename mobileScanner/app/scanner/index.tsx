import React, { useEffect, useRef, useState } from "react";
import {
  SafeAreaView,
  StatusBar,
  StyleSheet,
  Text,
  View,
  Platform,
  AppState,
  TextInput,
  TouchableOpacity,
  Alert,
} from "react-native";
import { Camera, CameraView } from "expo-camera";
import axios from "axios";
import { useRouter } from "expo-router";

export default function QRScanner() {
  const qrLock = useRef(false); 
  const [qrdata, setQrData] = useState<string | null>(null);
  const [rowData, setRowData] = useState<any>(null);
  const [hasPermission, setHasPermission] = useState<boolean | null>(null);
  const [loading, setLoading] = useState(false); 
  const [fuelAmount, setFuelAmount] = useState(""); 
  const router = useRouter();

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === "granted");
    })();
  }, []);

  const handleBarcodeScanned = async ({ data }: { data: string }) => {
    if (!qrLock.current && data) {
      qrLock.current = true; 
      setLoading(true); 

      try {
        const response = await axios.get(
          `https://eb0c-2402-4000-13ca-27c8-71b7-4243-5ecf-5998.ngrok-free.app/api/${data}`
        );
        setRowData(response.data.vehiclesDto);

        router.push(
          `/result?license_plate_no=${response.data.vehiclesDto.license_plate_no}&vehicleType=${response.data.vehiclesDto.vehicle_type}&fuelType=${response.data.vehiclesDto.fuel_type}&availableFuelCapacity=${response.data.vehiclesDto.availableFuelCapacity}&vehicleId=${response.data.vehiclesDto.vehicleId}`
        );
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setLoading(false); 
      }
    }
  };

  useEffect(() => {
    const appStateSubscription = AppState.addEventListener(
      "change",
      (nextAppState) => {
        if (nextAppState === "active") {
          qrLock.current = false; 
        }
      }
    );

    return () => {
      appStateSubscription.remove();
    };
  }, []);

  const handlePumpFuel = async() => {
    if (fuelAmount === "") {
      Alert.alert("Error", "Please enter the QR Code");
    } 
  
    
    try {
      const response = await axios.get(
        `https://eb0c-2402-4000-13ca-27c8-71b7-4243-5ecf-5998.ngrok-free.app/api/${fuelAmount}`
      );
      setRowData(response.data.vehiclesDto);

      router.push(
        `/result?license_plate_no=${response.data.vehiclesDto.license_plate_no}&vehicleType=${response.data.vehiclesDto.vehicle_type}&fuelType=${response.data.vehiclesDto.fuel_type}&availableFuelCapacity=${response.data.vehiclesDto.availableFuelCapacity}&vehicleId=${response.data.vehiclesDto.vehicleId}`
      );
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setLoading(false); 
    }
  
};

if (hasPermission === null) {
  return <Text>Requesting camera permission...</Text>;
}

if (hasPermission === false) {
  return <Text>No access to camera</Text>;
}

return (
  <SafeAreaView style={StyleSheet.absoluteFillObject}>
    <StatusBar hidden={Platform.OS === "android"} />
    <CameraView
      style={styles.cameraView}
      facing="back"
      onBarcodeScanned={handleBarcodeScanned} 
    />
    {loading && (
      <View style={styles.loadingContainer}>
        <Text style={styles.loadingText}>Processing QR Code...</Text>
      </View>
    )}

    <View style={styles.inputContainer}>
      <TextInput
        style={styles.input}
        placeholder="Enter Qr Code"
        placeholderTextColor="#888"
        value={fuelAmount}
        onChangeText={setFuelAmount}
      />
      <TouchableOpacity style={styles.button} onPress={handlePumpFuel}>
        <Text style={styles.buttonText}>Pump Fuel</Text>
      </TouchableOpacity>
    </View>
  </SafeAreaView>
);
}

const styles = StyleSheet.create({
  cameraView: {
    width: "90%", 
    height: 300, 
    alignSelf: "center", 
    marginTop: 50, 
    borderRadius: 15, 
    overflow: "hidden", 
  },
  loadingContainer: {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: [{ translateX: -100 }, { translateY: -50 }],
    backgroundColor: "rgba(0, 0, 0, 0.7)",
    padding: 20,
    borderRadius: 10,
  },
  loadingText: {
    color: "white",
    fontSize: 16,
    textAlign: "center",
  },
  inputContainer: {
    marginTop: 20,
    alignItems: "center",
    paddingHorizontal: 20,
  },
  input: {
    width: "90%",
    height: 50,
    backgroundColor: "#f0f0f0",
    borderRadius: 10,
    paddingHorizontal: 15,
    fontSize: 16,
    marginBottom: 15,
    borderWidth: 1,
    borderColor: "#ccc",
  },
  button: {
    width: "90%",
    height: 50,
    backgroundColor: "#007BFF",
    borderRadius: 10,
    justifyContent: "center",
    alignItems: "center",
  },
  buttonText: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
  },
});


