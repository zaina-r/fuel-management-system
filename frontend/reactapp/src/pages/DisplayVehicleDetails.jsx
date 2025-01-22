import React, { useState, useRef } from "react";
import { QRCodeSVG } from "qrcode.react";
import { toPng } from "html-to-image";

const DisplayVehicleDetails = () => {
  const [qrData, setQrData] = useState({
    qrCode: "https://example.com/vehicle",
    textAbove: "Vehicle QR Code",
    textBelow: "Scan this code for vehicle details",
  });

  const qrRef = useRef(null);

  const downloadQR = () => {
    if (qrRef.current) {
      toPng(qrRef.current, { cacheBust: true })
        .then((dataUrl) => {
          const link = document.createElement("a");
          link.download = "vehicle-qr-code.png";
          link.href = dataUrl;
          link.click();
        })
        .catch((err) => {
          console.error("Error generating image:", err);
        });
    }
  };

  return (
    <div style={{ textAlign: "center", padding: "20px" }}>
      <div
        ref={qrRef}
        style={{
          display: "inline-block",
          padding: "20px",
          border: "1px solid #ccc",
          borderRadius: "10px",
          backgroundColor: "#fff",
        }}
      >
        <div style={{ marginBottom: "10px", fontSize: "18px", fontWeight: "bold" }}>
          {qrData.textAbove}
        </div>
        <QRCodeSVG value={qrData.qrCode} size={250} />
        <div style={{ marginTop: "10px", fontSize: "16px", color: "#555" }}>
          {qrData.textBelow}
        </div>
      </div>
      <div style={{ marginTop: "20px" }}>
        <button
          onClick={downloadQR}
          style={{
            backgroundColor: "#007BFF",
            color: "white",
            padding: "10px 20px",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
          }}
        >
          Download QR Code
        </button>
      </div>
    </div>
  );
};

export default DisplayVehicleDetails;

