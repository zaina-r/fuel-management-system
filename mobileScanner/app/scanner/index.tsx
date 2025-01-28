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

