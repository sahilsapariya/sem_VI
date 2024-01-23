import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/pages/Home";
import Navbar from "./components/common/Navbar";
import Footbar from "./components/common/Footbar";
import Projects from "./components/pages/Projects";

const App = () => {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route path="/" index exact element={<Home />} />
          <Route path="/projects" element={<Projects />} />
        </Routes>

        <Footbar />
      </Router>
    </div>
  );
};

export default App;
