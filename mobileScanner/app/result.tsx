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