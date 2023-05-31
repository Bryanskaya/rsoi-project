import { Box } from "@chakra-ui/react";
import GetMyReservations from "postAPI/likes/GetMyReservations";
import React from "react";

import styles from "./LikedRecipesPage.module.scss";
import ReservationMap from "components/ReservationMap/ReservationMap";

interface LikedRecipesProps {}

const LikedRecipes: React.FC<LikedRecipesProps> = () => {
  return (
    <Box className={styles.main_box}>
      <ReservationMap getCall={GetMyReservations} />
    </Box>
  );
};

export default LikedRecipes;
