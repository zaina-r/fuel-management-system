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

  const saveData = async (id: string,name:string,fuelStationId:string) => {
    try {
      await AsyncStorage.setItem('stationId',id);
      await AsyncStorage.setItem('stationName',name);
      await AsyncStorage.setItem('fuelStationId',fuelStationId.toString());
      // setStationId(id);
      const stId= await AsyncStorage.getItem('stationId');
      const stName= await AsyncStorage.getItem('stationName');
      const fsId= await AsyncStorage.getItem('fuelStationId');
      setStationId(stId);
      setStationName(stName);
      setFuel_Station_Id(fsId);

    } catch (error) {                                                                                        
      console.error('Error saving data:', error);
    }
  };    

  const handleCheck=async()=>{
    console.log(code)
          try{
               const response=await axios.post(`https://eb0c-2402-4000-13ca-27c8-71b7-4243-5ecf-5998.ngrok-free.app/api/station/mobile/${code}`,
                {},
                  {
                    headers: {
                      "Content-Type": "application/json",
                    },
                  })
                  console.log(response.data)
                  if(response.data.statusCode===200){
                    setChekced(true);
                    saveData(response.data.stationDto.stationId,response.data.stationDto.dealerName,response.data.stationDto.id);
                    Alert.alert("Code Confirmed Successfully");
                 
                  }
                  

                
                
               
          }catch(err){
                console.log(err);
                setChekced(false);
          }finally{
            setCode('')
          }
  }

  return (
    <SafeAreaView style={styles.container}>
      <Stack.Screen options={{ title: "Overview", headerShown: false }} />
      <Text style={styles.title}>National Fuel Pass</Text>
      <Text>{stationId}</Text>
      <Text>{stationName}</Text>
      <Text>{fuel_station_Id}</Text>

      

      <View style={{ gap: 20, width: '80%', alignItems: 'center' }}>
      
        <TextInput
          style={styles.inputBox}
          value={code}
          onChangeText={setCode}
          placeholder="Enter code"
          placeholderTextColor="rgba(0, 0, 0, 0.5)" 
          keyboardType="default"
        />

      
        <Pressable onPress={requestPermission}>
          <Text style={styles.buttonStyle}>Request Permissions</Text>
        </Pressable>


        
      {checked &&
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
    </Link> }  

        <Pressable 
          onPress={() => {
            if (code.trim()) {
              alert(`Code entered: ${code}`);
            } else {
              alert("Please enter a code first.");
            }
          }}
          style={styles.scanButton}
        >
          <Text style={styles.buttonText} onPress={handleCheck}>Chech Code</Text>
        </Pressable>
      </View>
    </SafeAreaView>
  );
}
 
const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: "#f5f5f5",
    justifyContent: "space-around",
    paddingVertical: 80,
  },
  title: {
    color: "black",
    fontSize: 40,
    fontWeight: 'bold',
    marginBottom: 30,  
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
});



