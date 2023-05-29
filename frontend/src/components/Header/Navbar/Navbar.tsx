import React from "react";
import { Box, Link } from "@chakra-ui/react";
import { useCookies } from "react-cookie";

import styles from "./Navbar.module.scss";


import { Logout as LogoutQuery } from "postAPI/accounts/Logout";

import { navItems } from "./items";
import AuthActions from "./AuthActions";
import NoauthActions from "./NoauthActions";

export interface NavbarProps {}
const Navbar: React.FC<NavbarProps> = () => {
    let [cookie, , removeCookie] = useCookies(['role', 'login', 'token']);
    let role = cookie.role ? cookie.role : '';
    let username = (localStorage.getItem('username') !== null ? localStorage.getItem('username') : '')!;

    const [items, ] = React.useState(navItems[role]);
    const logout = () => {
        localStorage.clear();
        LogoutQuery(removeCookie)
        window.location.href = '/';
    }

    return (
    <Box className={styles.navbar}>
        <Box className={styles.navpages}> {items.map(item =>
            <Link key={item.name} href={item.ref}> {item.name} </Link>
        )} </Box>
        { username !== '' && <AuthActions login={username.slice(0, 10)} logout={logout} />}
        { username === '' && <NoauthActions /> }
    </Box>
    )
}

export default React.memo(Navbar);
