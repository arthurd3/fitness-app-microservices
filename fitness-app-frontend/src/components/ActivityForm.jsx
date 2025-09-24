import { Box, duration, FormControl, InputLabel, Menu, MenuItem, Select, TextField } from '@mui/material'
import React, { useState } from 'react'

const ActivityForm = () => {

  const [activity , setActivity] = useState({
    type: "RUNNING" , duration: '' , caloriesBurned: '' ,
    additionalMetrics: {}
  })

  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
      await addActivity(activity);
      onActivityAdded();
      setActivity({type: "RUNNING" , duration: '' , caloriesBurned: ''});
    }catch (error) {

    }
  }

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mb: 4 }}>
      <FormControl fullWidth sx={{mb:2}}>
        <InputLabel>Activity Type</InputLabel>
        <Select value={activity.type} onChange={(e) => setActivity({...activity, type: e.target.value})}>
          <MenuItem value="RUNNING">Running</MenuItem>
          <MenuItem value="WALKING">Walking</MenuItem>
          <MenuItem value="CYCLING">Cycling</MenuItem>
        </Select>
      </FormControl>
      <TextField fullWidth label="Duration (Minutes)" type='number' sx={{mb:2}} onChange={(e) => setActivity({...activity, type: e.target.value})}/>
    </Box>
  )
}

export default ActivityForm