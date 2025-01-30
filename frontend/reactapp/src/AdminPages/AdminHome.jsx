import React, { useEffect, useState } from "react";
import Admin from "../apiservice/Admin";

const AdminHome = () => {
  const [registeredStationCount, setRegisteredStationCount] = useState();
  const [totalStations, setTotalStations] = useState();
  const [activeStations,setActiceStation]=useState();
  const [inactiveStation,setInactiveStation]=useState();
  const [petrolCapacity,setPetrolCapacity]=useState();
  const [dieselCapacity,setDieselCapacity]=useState();
  const [registeredVehiclesCount, setRegisteredVehiclesCount]=useState();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response1 = await Admin.getRegisterdStations();
        setRegisteredStationCount(response1.stationDtosList.length);

        const response2 = await Admin.getStations();
        setTotalStations(response2.stationWithStatusDTOList.length);

        const response3 = await Admin.getRegisterdStations();
        const filteredStations = response3.stationDtosList.filter((station) => {
          return (
            station.fuel.availableDieselQuantity > 0 || station.fuel.availablePetrolQuantity > 0
          );
        });
        
        
        setActiceStation(filteredStations.length);
        setInactiveStation(registeredStationCount - filteredStations.length);
        console.log(inactiveStation)

        const totalPetrolCapacity = response1.stationDtosList.reduce(
          (sum, station) => sum + station.fuel.availablePetrolQuantity,    
          0
        );
        setPetrolCapacity(parseFloat(totalPetrolCapacity.toFixed(2)));
        
       
        const totalDieselCapacity = response1.stationDtosList.reduce(
          (sum, station) => sum + station.fuel.availableDieselQuantity,
          0
        );
        setDieselCapacity(parseFloat(totalDieselCapacity.toFixed(2)));
        
        

        const response4=await Admin.getRegisterdVehicles();
        setRegisteredVehiclesCount(response4.vehiclesDtoList.length);
        console.log(registeredVehiclesCount)
        
      } catch (err) {
        console.error("Error fetching data:", err);
      }
    };

    fetchData();
  }, []);
  const cards = [
    {
      title: "Registered Vehicle",
      description: "Add, edit, and view registered vehicles.",
      icon: "🚗",
      bgColor: "bg-gradient-to-r from-blue-500 to-blue-700",
      amt:registeredVehiclesCount
    },
    {
      title: "Registered Stations",
      description: "Track fuel allocations and consumption.",
      icon: "⛽",
      bgColor: "bg-gradient-to-r from-green-500 to-green-700",
      amt:registeredStationCount
    },
    {
      title: "Total Stations",
      description: "Manage admin and user accounts.",
      icon: "👤",
      bgColor: "bg-gradient-to-r from-purple-500 to-purple-700",
      amt:totalStations
    },
    {
      title: "activeStations",
      description: "Generate and analyze system reports.",
      icon: "📊",
      bgColor: "bg-gradient-to-r from-yellow-500 to-yellow-700",
      amt:activeStations
    },
    {
      title: "In Active Stations",
      description: "Send and manage alerts and notifications.",
      icon: "🔔",
      bgColor: "bg-gradient-to-r from-red-500 to-red-700",
      amt:inactiveStation
    },
    {
      title: "Total Available petrolCapacity",
      description: "Configure system preferences.",
      icon: "🛢️",
      bgColor: "bg-gradient-to-r from-gray-500 to-gray-700",
      amt:petrolCapacity
    },
    {
      title: "Total Available DieselCapacity",
      description: "Access help and support resources.",
      icon: "🛢",
      bgColor: "bg-gradient-to-r from-pink-500 to-pink-700",
      amt:dieselCapacity
    },
  ];

  return (
    <div className="p-6 text-white">
      <h1 className="text-2xl font-bold mb-6">Admin Panel</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {cards.map((card, index) => (
          <div
            key={index}
            className={`p-6 rounded-lg shadow-lg hover:scale-105 transition-transform duration-300 ${card.bgColor}`}
          >
            <div className="flex justify-between items-center">
            <div className="text-4xl mb-4">{card.icon}</div>
             
            <p className="text-2xl ">{card.amt}</p>

            </div>
            <h2 className="text-xl font-bold mb-2">{card.title}</h2>
            <p className="text-sm">{card.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminHome;
