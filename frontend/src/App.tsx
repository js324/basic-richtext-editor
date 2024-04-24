import { useState, useEffect, FormEvent } from 'react'
import axios from 'axios';
import ContentEditable, { ContentEditableEvent } from "react-contenteditable";
import './App.css'

axios.defaults.baseURL = 'http://localhost:8080/api';

function App() {
  const [html, setHTML] = useState("");
  const [userId, setUserId] = useState("default");
  const handleChange = (event: ContentEditableEvent) => {
    setHTML(event.target.value);
  };

  function saveHTML() {
    axios.put(`/content`, {
      id: userId, content: html 
    }
    ).then(() => {
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
    axios.get(`/content`, {
      params: { id: userId }
    }
    ).then((res) => {
      setHTML(res.data);
    })
    .catch(e => alert(`Getting data failed: ${e.message}`))
  }
  function updateId(event : FormEvent<HTMLInputElement>) {
    setUserId(event.currentTarget.value)
  }
  useEffect(() => {
    getHTML();
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
