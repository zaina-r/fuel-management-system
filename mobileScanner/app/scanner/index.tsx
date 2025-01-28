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