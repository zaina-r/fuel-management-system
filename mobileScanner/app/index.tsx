import {
  Image,
  View,
  Text,
  StyleSheet,
  SafeAreaView,
  Pressable,
  TextInput,
  Alert,
} from "react-native";
import { Link, Stack } from "expo-router";
import { useCameraPermissions } from "expo-camera";
import { useState } from "react";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";
import React from "react";

export default function Home() {
  const [permission, requestPermission] = useCameraPermissions();
  const [code, setCode] = useState("");
  const [checked, setChecked] = useState(false);
  const [stationName, setStationName] = useState("");
  const [stationId, setStationId] = useState("");
  const [fuelStationId, setFuelStationId] = useState("");
  const isPermissionGranted = Boolean(permission?.granted);

  const saveData = async (id, name, fuelStationId) => {
    try {
      await AsyncStorage.setItem("stationId", id);
      await AsyncStorage.setItem("stationName", name);
      await AsyncStorage.setItem("fuelStationId", fuelStationId.toString());

      const stId = await AsyncStorage.getItem("stationId");
      const stName = await AsyncStorage.getItem("stationName");
      const fsId = await AsyncStorage.getItem("fuelStationId");

      setStationId(stId);
      setStationName(stName);
      setFuelStationId(fsId);
    } catch (error) {
      console.error("Error saving data:", error);
    }
  };

  const handleCheck = async () => {
    try {
      const response = await axios.post(
        `https://3e32-192-248-24-51.ngrok-free.app/api/station/mobile/${code}`,
        {},
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.data.statusCode === 200) {
        setChecked(true);
        saveData(
          response.data.stationDto.stationId,
          response.data.stationDto.dealerName,
          response.data.stationDto.id
        );
        Alert.alert("Code Confirmed Successfully");
      }
    } catch (err) {
      console.log(err);
      setChecked(false);
    } finally {
      setCode("");
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <Stack.Screen options={{ title: "Overview", headerShown: false }} />
      <View>
        
        <Image source={require("./images/Monochrome Ilustration Graffiti Logo new.png")} style={styles.headerImage} />
        {/* <Text style={styles.title}>National Fuel Pass</Text> */}
        <View style={{marginTop:300}}>
          {stationId ? (
            <Text style={styles.stationDetail}>
              {stationId}: {stationName}
            </Text>
          ) : (
            <Text style={styles.station}></Text>
          )}
        </View>
      </View>

      <View style={{ gap: 20, width: "80%", alignItems: "center" }}>
        {!checked && (
          <>
            <TextInput
              style={styles.inputBox}
              value={code}
              onChangeText={setCode}  
              placeholder="Enter login code"
              placeholderTextColor="gray"
              keyboardType="default"
            />

            <Pressable
              style={styles.scanButton}
              onPress={handleCheck}
            >
              <Text style={styles.buttonText}>Check Code</Text>
            </Pressable>
          </>
        )}

        <Pressable onPress={requestPermission}>
          <Text style={styles.buttonStyle}>Request Permissions</Text>
        </Pressable>

        {checked && (
          <Link href={"/scanner"} asChild>
            <Pressable disabled={!isPermissionGranted}>
              <Text
                style={[
                  styles.buttonStyle,
                  { opacity: !isPermissionGranted ? 0.5 : 1 },
                ]}
              >
                Scan Code
              </Text>
            </Pressable>
          </Link>
        )}
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: "#0F172A",
    justifyContent: "space-around",
    paddingVertical: 80,
  },
  title: {
    color: "black",
    fontSize: 40,
    fontWeight: "bold",
  },
  inputBox: {
    height: 50,
    width: "100%",
    borderColor: "#ccc",
    borderWidth: 1,
    borderRadius: 8,
    fontSize: 18,
    paddingLeft: 10,
    marginBottom: 20,
    color:"white"
  },
  buttonStyle: {
    color: "#0E7AFE",
    fontSize: 20,
    textAlign: "center",
    marginTop: 10,
  },
  scanButton: {
    backgroundColor: "#0E7AFE",
    paddingVertical: 10,
    paddingHorizontal: 20,
    borderRadius: 8,
    marginTop: 10,
  },
  buttonText: {
    color: "#fff",
    fontSize: 20,
  },
  station: {
    textAlign: "center",
    marginTop: 10,
  },
  stationDetail: {
    textAlign: "center",
    marginTop: 10,
    fontWeight: "bold",
    fontSize: 20,
    color: "white",
  },
  headerImage:{
    width:250,
    height: 200,
    resizeMode: "contain",
    marginBottom: 30,
    marginLeft: 40,
    
  }
});
