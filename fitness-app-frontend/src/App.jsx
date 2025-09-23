import { Button } from "@mui/material"
import { BrowserRouter as Router , Navigate , Route , Routes , useLocation} from "react-router"

function App() {
  return (
    <Router>
      <Button variant="contained" color="#dc004e">Login </Button>
    </Router>
  )
}

export default App
