import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { getActivityDetails } from '../services/api';

const ActivityDetail = () => {

  const {id} = useParams();
  const [activity, setActivity] = useState(null);
  const [recommendation , setRecommendation] = useState(null);

  useEffect(() => {
    const fetchActivityDetail = async () => {
      try {
        const response = await getActivityDetails(id);
        setActivity(response.data);
        setRecommendation(response.data.recommendation);
      } catch (error) {
        console.log(error)
      }
    }
  }, [id]);


  return (
    <div>ActivityDetail</div>
  )
}

export default ActivityDetail