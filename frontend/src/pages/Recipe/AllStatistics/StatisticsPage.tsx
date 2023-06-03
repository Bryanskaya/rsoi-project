import { Box } from "@chakra-ui/react";
import React from "react";

import styles from "./StatisticsPage.module.scss";
import GetAvgServiceTime from "postAPI/statistics/GetAvgServiceTime";
import StatisticsMap from "components/StatisticsMap/StatisticsMap";
import GetAvgQueryTime from "postAPI/statistics/GetAvgQueryTime";
import GetPopular from "postAPI/hotels/GetPopular";

interface StatisticsProps {}

const StatisticsPage: React.FC<StatisticsProps> = () => {
  return (
    <Box className={styles.main_box}>
      <StatisticsMap getCall={GetAvgServiceTime} getCallQuery={GetAvgQueryTime} getCallPopularHotels={GetPopular} />
    </Box>
  );
};

export default StatisticsPage;
