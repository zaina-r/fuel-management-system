import React, { useEffect, useState } from "react";
import Admin from "../apiservice/Admin";
import { FiAlertCircle, FiCheckCircle, FiInfo, FiRefreshCw } from "react-icons/fi";
import { FaGasPump, FaMapMarkerAlt, FaUserTie, FaIdCard } from "react-icons/fa";
import { BsFuelPump } from "react-icons/bs";

const Notification = () => {
  const [stationDetails, setStationDetails] = useState([]);
  const [filteredStations, setFilteredStations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedStation, setSelectedStation] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [refreshing, setRefreshing] = useState(false);
  const [stats, setStats] = useState({
    totalStations: 0,
    noFuelStations: 0,
    criticalStations: 0
  });

  const fetchStations = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await Admin.getRegisterdStations();

      if (response.statusCode === 200) {
        const stations = response.stationDtosList || [];
        setStationDetails(stations);

        const noFuelStations = stations.filter(
          station => !station.fuel || station.fuel === "N/A"
        );
        setFilteredStations(noFuelStations);

        const criticalStations = stations.filter(
          station => station.fuelStatus === "CRITICAL"
        ).length;

        setStats({
          totalStations: stations.length,
          noFuelStations: noFuelStations.length,
          criticalStations
        });
      } else {
        throw new Error(response.message || "Failed to fetch stations");
      }
    } catch (err) {
      console.error("Error fetching stations:", err);
      setError(err.message || "Failed to load station data");
    } finally {
      setLoading(false);
      setRefreshing(false);
    }
  };

  useEffect(() => {
    fetchStations();
  }, []);

  const handleRefresh = () => {
    setRefreshing(true);
    fetchStations();
  };

  const handleStationClick = (station) => {
    setSelectedStation(station);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedStation(null);
  };

  const renderStationCard = (station) => (
    <div 
      key={station.stationId}
      onClick={() => handleStationClick(station)}
      className="mb-4 p-4  rounded-lg shadow-md border-l-4 border-red-500 cursor-pointer transition-all hover:shadow-lg hover:-translate-y-1"
    >
      <div className="flex justify-between items-start">
        <div>
          <h3 className="text-lg font-semibold text-gray-800 flex items-center">
            <FaIdCard className="text-blue-500 mr-2" />
            {station.stationId}
          </h3>
          <div className="mt-2 text-gray-600">
            <p className="mb-1 flex items-center">
              <FaMapMarkerAlt className="text-gray-500 mr-2" />
              <span className="font-medium">Address:</span> {station.stationAddress}
            </p>
            <p className="mb-1 flex items-center">
              <FaUserTie className="text-gray-500 mr-2" />
              <span className="font-medium">Dealer:</span> {station.dealerName}
            </p>
            <p className="mb-0 flex items-center">
              <BsFuelPump className="text-red-500 mr-2" />
              <span className="font-medium">Fuel Status:</span> 
              <span className={`ml-2 px-2 py-1 rounded-full text-xs font-semibold ${
                !station.fuel || station.fuel === "N/A" 
                  ? "bg-red-100 text-red-800" 
                  : "bg-yellow-100 text-yellow-800"
              }`}>
                {!station.fuel || station.fuel === "N/A" ? "NO FUEL" : station.fuel}
              </span>
            </p>
          </div>
        </div>
        <div>
          {!station.fuel || station.fuel === "N/A" ? (
            <FiAlertCircle className="text-red-500 text-2xl" />
          ) : (
            <FiInfo className="text-yellow-500 text-2xl" />
          )}
        </div>
      </div>
    </div>
  );

  const renderStatsCards = () => (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
      <div className="bg-gray-300 p-4 rounded-lg shadow-sm hover:shadow-md transition-shadow">
        <div className="flex justify-between items-center">
          <div>
            <p className="text-sm text-gray-500 uppercase font-medium">Total Stations</p>
            <h3 className="text-2xl font-bold text-gray-800">{stats.totalStations}</h3>
          </div>
          <div className="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
            <FaGasPump className="text-blue-500 text-xl" />
          </div>
        </div>
      </div>
      
      <div className="bg-gray-300 p-4 rounded-lg shadow-sm hover:shadow-md transition-shadow">
        <div className="flex justify-between items-center">
          <div>
            <p className="text-sm text-gray-500 uppercase font-medium">No Fuel</p>
            <h3 className="text-2xl font-bold text-gray-800">{stats.noFuelStations}</h3>
          </div>
          <div className="w-10 h-10 rounded-full bg-red-100 flex items-center justify-center">
            <FiAlertCircle className="text-red-500 text-xl" />
          </div>
        </div>
      </div>
      
      <div className="bg-gray-300 p-4 rounded-lg shadow-sm hover:shadow-md transition-shadow">
        <div className="flex justify-between items-center">
          <div>
            <p className="text-sm text-gray-500 uppercase font-medium">Critical</p>
            <h3 className="text-2xl font-bold text-gray-800">{stats.criticalStations}</h3>
          </div>
          <div className="w-10 h-10 rounded-full bg-yellow-100 flex items-center justify-center">
            <FiAlertCircle className="text-yellow-500 text-xl" />
          </div>
        </div>
      </div>
    </div>
  );

  return (
    <div className="container mx-auto px-4 py-6">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-6">
        <h1 className="text-2xl font-bold text-gray-800 flex items-center">
          <FaGasPump className="mr-2 text-blue-500" />
            <span className="text-white">Notifications</span>
        </h1>
        <button 
          onClick={handleRefresh}
          disabled={refreshing}
          className="mt-4 md:mt-0 px-4 py-2 border border-blue-500 text-blue-500 rounded-md hover:bg-blue-50 transition-colors flex items-center"
        >
          {refreshing ? (
            <>
              <svg className="animate-spin -ml-1 mr-2 h-4 w-4 text-blue-500" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              Refreshing...
            </>
          ) : (
            <>
              <FiRefreshCw className="mr-2" />
              Refresh Data
            </>
          )}
        </button>
      </div>

      {error && (
        <div className="mb-6 p-4 bg-red-100 border-l-4 border-red-500 text-red-700">
          <div className="flex items-center">
            <FiAlertCircle className="mr-2" />
            <p>{error}</p>
          </div>
        </div>
      )}

      {renderStatsCards()}

      <div className="bg-gray-300 rounded-lg shadow-md overflow-hidden">
        <div className="px-6 py-4 bg-gray-800 text-white">
          <h2 className="text-lg font-semibold flex items-center">
            <FiAlertCircle className="mr-2" />
            Stations Requiring Attention ({filteredStations.length})
          </h2>
        </div>
        <div className="p-6">
          {loading ? (
            <div className="text-center py-8">
              <div className="inline-block animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-blue-500 mb-2"></div>
              <p className="text-gray-600">Loading station data...</p>
            </div>
          ) : filteredStations.length > 0 ? (
            <div className="space-y-4">
              {filteredStations.map(renderStationCard)}
            </div>
          ) : (
            <div className="p-4 bg-green-100 rounded-md text-green-800 flex items-center">
              <FiCheckCircle className="mr-2" />
              All stations currently have fuel available. No critical alerts!
            </div>
          )}
        </div>
      </div>

      {/* Station Details Modal */}
      {showModal && selectedStation && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
          <div className="bg-white rounded-lg shadow-xl w-full max-w-4xl max-h-[90vh] overflow-y-auto">
            <div className="px-6 py-4 bg-gray-800 text-white flex justify-between items-center">
              <h3 className="text-lg font-semibold flex items-center">
                <FaGasPump className="mr-2" />
                Station Details
              </h3>
              <button onClick={handleCloseModal} className="text-white hover:text-gray-300">
                <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
            <div className="p-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <h4 className="text-lg font-semibold text-gray-800 flex items-center mb-4">
                    <FaIdCard className="text-blue-500 mr-2" />
                    {selectedStation.stationId}
                  </h4>
                  <div className="space-y-3">
                    <div className="flex items-start">
                      <FaMapMarkerAlt className="text-gray-500 mt-1 mr-2 flex-shrink-0" />
                      <div>
                        <p className="font-medium text-gray-700">Address:</p>
                        <p className="text-white">{selectedStation.stationAddress}</p>
                      </div>
                    </div>
                    <div className="flex items-start">
                      <FaUserTie className="text-gray-500 mt-1 mr-2 flex-shrink-0" />
                      <div>
                        <p className="font-medium text-gray-700">Dealer:</p>
                        <p className="text-gray-600">{selectedStation.dealerName}</p>
                      </div>
                    </div>
                    <div className="flex items-start">
                      <BsFuelPump className="text-red-500 mt-1 mr-2 flex-shrink-0" />
                      <div>
                        <p className="font-medium text-gray-700">Fuel Status:</p>
                        <p className="text-gray-600">
                          <span className={`px-2 py-1 rounded-full text-xs font-semibold ${
                            !selectedStation.fuel || selectedStation.fuel === "N/A" 
                              ? "bg-red-100 text-red-800" 
                              : "bg-yellow-100 text-yellow-800"
                          }`}>
                            {!selectedStation.fuel || selectedStation.fuel === "N/A" ? "NO FUEL" : selectedStation.fuel}
                          </span>
                        </p>
                      </div>
                    </div>
                  </div>
                  <div className="mt-6 space-y-2">
                    <button className="w-full bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded-md transition-colors">
                      Contact Dealer
                    </button>
                    <button className="w-full border border-gray-300 hover:bg-gray-50 text-gray-700 py-2 px-4 rounded-md transition-colors">
                      View Full Details
                    </button>
                  </div>
                </div>
                <div>
                  <div className="bg-gray-100 rounded-lg h-64 mb-4 flex items-center justify-center text-gray-400">
                    [Map Placeholder]
                  </div>
                  <div className="bg-gray-100 rounded-lg h-48 flex items-center justify-center text-gray-400">
                    [Fuel Status Chart Placeholder]
                  </div>
                </div>
              </div>
            </div>
            <div className="px-6 py-4 bg-gray-50 border-t border-gray-200 flex justify-end space-x-3">
              <button 
                onClick={handleCloseModal}
                className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-100 transition-colors"
              >
                Close
              </button>
              <button 
                onClick={handleCloseModal}
                className="px-4 py-2 bg-blue-500 rounded-md text-white hover:bg-blue-600 transition-colors"
              >
                Save Changes
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Notification;