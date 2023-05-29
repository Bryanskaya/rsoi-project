import { Box, HStack, Image, Link, Text, VStack } from "@chakra-ui/react";
import React from "react";

import { Hotel as HotelI } from "types/Hotel";

import StarBox from "components/Boxes/Star";
import FullLikeBox from "components/Boxes/FullLike";

import styles from "./RecipeCard.module.scss";
import GetImageUrl from "postAPI/likes/Get";


interface RecipeProps extends HotelI {}

const RecipeCard: React.FC<RecipeProps> = (props) => {
  var path = "/recipes/" + props.hotelUid;
  path = "#";
  const [imageUrl, setImageUrl] = React.useState("https://media.discordapp.net/attachments/791290400086032437/1112498073478889502/default-fallback-image.png?width=720&height=480");

  async function getImageUrl() {
    var data = await GetImageUrl(props.hotelUid);
    if (data.status === 200) {
      setImageUrl(data.content);
    }
  }

  getImageUrl();

  return (
    <Link className={styles.link_div} href={path}>
      <Box className={styles.main_box}>
        <Image src={imageUrl} className={styles.image_div} />

        <Box className={styles.info_box}>
          <VStack>
            <Box className={styles.title_box}>
              <Text>{props.name}</Text>
            </Box>

            <Box className={styles.description_box}>
              <Text>{props.country}</Text>
            </Box>
            <Box className={styles.description_box}>
              <Text>{props.city}</Text>
            </Box>
            <Box className={styles.description_box}>
              <Text>{props.address}</Text>
            </Box>
          </VStack>

          <HStack>
            <StarBox duration={props.stars} />
            <FullLikeBox price={props.price} />
          </HStack>
        </Box>
      </Box>
    </Link>
  );
};

export default RecipeCard;
