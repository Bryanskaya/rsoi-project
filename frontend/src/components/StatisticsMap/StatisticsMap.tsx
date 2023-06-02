import { Box, Text } from "@chakra-ui/react";
import React from "react";

import styles from "./Statistics.module.scss";
import { AllAvgServiceTimeResp } from "postAPI";
import StatisticsAvgServiceTimeCard from "components/StatisticsAvgServiceTimeCard/StatisticsAvgServiceTimeCard";


interface StatisticsBoxProps {
    searchQuery?: string
    getCall: () => Promise<AllAvgServiceTimeResp>
}

type State = {
    avgServiceTime?: any
}


class StatisticsMap extends React.Component<StatisticsBoxProps, State> {
    constructor(props) {
        super(props);
        this.state = {
            avgServiceTime: []
        }
    }

    async getAllAvgServiceTime() {
        var data = await this.props.getCall();
        if (data.status === 200)
            this.setState({avgServiceTime: data.content})
    }

    componentDidMount() {
        this.getAllAvgServiceTime()
    }

    render() {
        return (
            <Box className={styles.map_box}>
                <Text className={styles.title_box}>
                    Общие технические характеристики
                </Text>

                {this.state.avgServiceTime.map(item => <StatisticsAvgServiceTimeCard {...item} key={item.id}/>)}
            </Box>
        )
    }
}

export default React.memo(StatisticsMap);