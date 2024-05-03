import { useState } from 'react'
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { InView } from "react-intersection-observer";
import './ScrollableAccordion.css';
export interface IScrollableAccordionProps{
    headerElement: React.ReactElement | string; 
    children?: React.ReactNode,
}
function ScrollableAccordion({headerElement, children}: IScrollableAccordionProps) {
    const [open, setOpen] = useState(false);
    function toggleAccordion() {
        setOpen(!open);
    }
    return (
        <>
        <InView onChange={(inView, entry) => {if (inView) setOpen(true)}}>
            <Accordion expanded={open}>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel-content"
                className="sa-panel-header"
                onClick={toggleAccordion}
            >
                {headerElement}
            </AccordionSummary>
            <AccordionDetails className="sa-panel-body">
                {children}
            </AccordionDetails>
            </Accordion>
        </InView>
        </>
    ); 
}

export default ScrollableAccordion;