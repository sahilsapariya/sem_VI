import { useEffect, useState } from "react";
import axios from "axios";

const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async (url) => {
      setLoading(true);
      try {
        const requestOption = {
          headers: {
            "Content-Type": "application/json",
          },
        };
        const response = await axios.get(url, requestOption);
        setData(response.data);
      } catch (error) {
        setError(error);
      }
      setLoading(false);
    };
    fetchData(url);
  }, [url]);

  return { data, loading, error };
};

export default useFetch;
