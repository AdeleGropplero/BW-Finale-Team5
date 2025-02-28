import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router";
import MyNavbar from "./components/MyNavbar";
import Homepage from "./components/Homepage";
import Cliente from "./components/Cliente";

function App() {
  return (
    <BrowserRouter>
      <MyNavbar />
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/cliente" element={<Cliente />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
