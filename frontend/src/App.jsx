import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router";
import MyNavbar from "./components/MyNavbar";
import Homepage from "./components/Homepage";
import Cliente from "./components/Cliente";
import Fattura from "./components/Fattura";
import Login from "./components/Login";
import AdminCheck from "./components/AdminCheck";

function App() {
  return (
    <BrowserRouter>
      <MyNavbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/utente" element={<Homepage />} />
        <Route path="/cliente" element={<Cliente />} />
        <Route path="/fattura" element={<Fattura />} />
        <Route path="/adminCheck" element={<AdminCheck />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
