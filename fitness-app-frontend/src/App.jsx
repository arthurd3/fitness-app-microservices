import { Button } from "@mui/material"
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter as Router , Navigate , Route , Routes , useLocation} from "react-router"
import { setCredentials } from "./store/authSlice";

function App() {

  const {token, tokenData , logIn , logOut , isAuthenticated } = useContext(AuthContext);
  const dispatch = useDispatch();
  const [authReady , setAuthReady] = useState(false);

  useEffect(() => {
    if(token){
      dispatch(setCredentials({token , user: tokenData}))
      setAuthReady(true);
    }
  }, [token , tokenData , dispatch])

  return (
    <Router>
      <Button variant="contained" color="#dc004e">Login </Button>
    </Router>
  )
}

export default App
