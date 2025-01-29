import { View, Text, StyleSheet, SafeAreaView, Pressable, TextInput, Alert } from "react-native";
import { Link, Stack } from "expo-router";
import { useCameraPermissions } from "expo-camera";
import { useState } from "react";
import axios from "axios";
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function Home() {
  const [permission, requestPermission] = useCameraPermissions();
  const [code, setCode] = useState("");  
  const [checked,setChekced]=useState(false);
  const [stationName,setStationName]=useState(""); 
  const [stationId,setStationId]=useState("");
  const [fuel_station_Id,setFuel_Station_Id]=useState("");
  const isPermissionGranted = Boolean(permission?.granted);
