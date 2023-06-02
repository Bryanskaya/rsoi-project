import { Box } from "@chakra-ui/react";
import React from "react";

import styles from "./AuthorRecipesPage.module.scss";
import GetAvgServiceTime from "postAPI/statistics/GetAvgServiceTime";
import StatisticsMap from "components/StatisticsMap/StatisticsMap";

interface AuthorRecipesProps {}

const AuthorRecipes: React.FC<AuthorRecipesProps> = () => {
  console.log("authorrecipes");
  return (
    <Box className={styles.main_box}>
      <StatisticsMap getCall={GetAvgServiceTime} />
      {/* <RecipeMap getCall={() => GetRecipes(login)} /> */}
    </Box>
  );
};

export default AuthorRecipes;
