import { Box } from "@chakra-ui/react";
import React from "react";
import RecipeCard from "../HotelCard";
import { AllHotelResp } from "postAPI"

import styles from "./RecipeMap.module.scss";

interface RecipeBoxProps {
    searchQuery?: string
    getCall: (title?: string) => Promise<AllHotelResp>
}

type State = {
    postContent?: any
}

class RecipeMap extends React.Component<RecipeBoxProps, State> {
    constructor(props) {
        super(props);
        this.state = {
            postContent: []
        }
    }

    async getAll() {
        var data = await this.props.getCall(this.props.searchQuery)
        if (data.status === 200)
            this.setState({postContent: data.content.items});
    }

    componentDidMount() {
        this.getAll()
    }

    componentDidUpdate(prevProps) {
        if (this.props.searchQuery !== prevProps.searchQuery) {
            this.getAll()
        }
    }

    render() {
        return (
            <Box className={styles.map_box}>
                {this.state.postContent.map(item => <RecipeCard {...item} key={item.id}/>)}
            </Box>
        )
    }
}

export default React.memo(RecipeMap);
