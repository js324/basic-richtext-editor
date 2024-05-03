import ScrollableAccordion from './ScrollableAccordion'
import './App.css'

function App() {
  return (
    <>
      <div style={{width: "1000px"}}> 
      <ScrollableAccordion headerElement={"testing 1"}>
        <div>testing 1</div>
      </ScrollableAccordion>
      <ScrollableAccordion headerElement={"testing 2"}>
        <div>testing 2</div>
      </ScrollableAccordion>
      <ScrollableAccordion headerElement={"testing 3"}>
        <div>testing 3</div>
      </ScrollableAccordion>
      </div>
    </>
  )
}

export default App
