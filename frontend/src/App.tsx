import { useState, useEffect, FormEvent, useRef } from 'react'
import axios from 'axios';
import ContentEditable, { ContentEditableEvent } from "react-contenteditable";
import './App.css'

axios.defaults.baseURL = 'http://localhost:8080/api';

function App() {
  const [userId, setUserId] = useState(localStorage.getItem("textarea-id") ?? "");
  const [html, setHTML] = useState("");
  
  const stateRef = useRef(userId);
  const handleChange = (event: ContentEditableEvent) => {
    setHTML(event.target.value);
  };

  function saveHTML() {
    axios.put(`/content`, {
      id: userId, content: html 
    }
    ).then(() => {
      localStorage.setItem(`textarea-${userId}`, html);
      alert("Saved successfullly!");
    })
    .catch(e => alert(`Saving data failed: ${e.message}`))
  }
  function resetHTML() {
    
    axios.put(`/content`, {
      id: userId, content: ""
    }
    ).then(() => {
      setHTML("");
      alert("Reset successfullly!");
    })
    .catch(e => alert(`Resetting data failed: ${e.message}`))
  }
  function getHTML() {
    const res = localStorage.getItem(`textarea-${userId}`)
    if (res) {
      setHTML(res);
      return;
    }
    axios.get(`/content`, {
      params: { id: userId }
    }
    ).then((res) => {
      setHTML(res.data);
    })
    .catch(e => {
      setHTML("");
      alert(`Getting data failed: ${e.response.data}`);
    })
  }
  function updateId(event : FormEvent<HTMLInputElement>) {
    setUserId(event.currentTarget.value);
    stateRef.current = event.currentTarget.value;
    console.log(userId)
  }
  useEffect(() => {
    getHTML();
  }, []);
  function saveData() {
    //need ref since listener only has access to initial render state
    localStorage.setItem(`textarea-${stateRef.current}`, html);
    localStorage.setItem(`textarea-id`, stateRef.current);
  }
  useEffect(() => {
    window.addEventListener('beforeunload', saveData);
    return () => {
      window.removeEventListener('beforeunload', saveData);
      saveData();
    };
  }, []);


  return (
    <>
      <div> 
        User ID: <input defaultValue={userId} onChange={updateId}/>
      </div>
      <div>
        <button onClick={saveHTML}>
          Save
        </button>
        <button onClick={resetHTML}>
          Reset
        </button>
        <button onClick={getHTML}>
          Get
        </button>
        <ContentEditable
          html={html} // innerHTML of the editable div
          disabled={false} // use true to disable edition
          onChange={handleChange} // handle innerHTML change
          className="content-div"
        />
      </div>
    </>
  );
}

export default App;
