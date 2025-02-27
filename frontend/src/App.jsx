import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router";
import MyNavbar from "./components/MyNavbar";
import Homepage from "./components/Homepage";
import InserisciCliente from "./components/InserisciCliente";

function App() {
  return (
    <BrowserRouter>
      <MyNavbar />
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/cliente" element={<InserisciCliente />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
