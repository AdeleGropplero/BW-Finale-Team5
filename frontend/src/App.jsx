import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes } from "react-router";
import MyNavbar from "./components/MyNavbar";

function App() {
  return (
    <BrowserRouter>
      <MyNavbar />
      <Routes></Routes>
    </BrowserRouter>
  );
}

export default App;
