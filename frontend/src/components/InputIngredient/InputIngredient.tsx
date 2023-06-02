import { Box, Button, useDisclosure } from '@chakra-ui/react'
import { useState } from 'react';
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
} from '@chakra-ui/react'
import React from 'react'

import AddIcon from 'components/Icons/Add'
import { DateReservation as DateReservationT } from 'types/DateReservation';
import RoundButton from "components/RoundButton/RoundButton";

import styles from "./InputIngredient.module.scss"
import DateInputBox from 'components/DateInputBox/DateInputBox'


interface DateContextType {
  startDate: string,
  setStartDate: React.Dispatch<React.SetStateAction<string>>,
  endDate: string,
  setEndDate: React.Dispatch<React.SetStateAction<string>>
}

export const DateContext = React.createContext<DateContextType | undefined>(undefined);


export default function IngredientInput(props) {
  const { isOpen, onOpen, onClose } = useDisclosure()
  const [ startDate, setStartDate] = useState('');
  const [ endDate, setEndDate] = useState('');

  var data: DateReservationT = { startDate: new Date(), endDate: new Date() }

  async function put() { 
    data.startDate = new Date(startDate);
    data.endDate = new Date(endDate);
    await props.putCallback(data)
    
    onClose()
  }

  return (
    <>
      <RoundButton onClick={onOpen}>
          Забронировать
      </RoundButton>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent className={styles.dark_bg}>
          <ModalHeader>Выберете даты въезда и выезда</ModalHeader>
          <ModalCloseButton />
          <ModalBody className={styles.model_body}>
            <Box>
              <DateContext.Provider value={{ startDate, setStartDate, endDate, setEndDate }}>
                <DateInputBox />
              </DateContext.Provider>
            </Box>

            <Button className={styles.ready_btn} onClick={put}>
              <AddIcon className={styles.img_btn} />
            </Button>
          </ModalBody>
          <ModalFooter/>
        </ModalContent>
      </Modal>
    </>
  )
}
