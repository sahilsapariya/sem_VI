import Home from "./components/pages/Home";
import { ThemeProvider } from "./context/ThemeContext";

function App() {
  return (
    <div className="App">
      <ThemeProvider>
        <Home />
      </ThemeProvider>
    </div>
  );
}

export default App;
