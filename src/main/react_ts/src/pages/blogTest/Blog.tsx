import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import GitHubIcon from '@mui/icons-material/GitHub';
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import Header from './Header';
import MainFeaturedPost from './MainFeaturedPost';
import FeaturedPost from './FeaturedPost';
import Main from './Main';
import Sidebar from './Sidebar';
import Footer from './Footer';
// import post1 from './blog-post.1.md';
// import post2 from './blog-post.2.md';
// import post3 from './blog-post.3.md';

const sections = [
    {title: '인기', url: '#'},
    {title: '팔로잉', url: '#'},
    {title: '사진', url: '#'},
    {title: '집들이', url: '#'},
    {title: '노하우', url: '#'},
    {title: '전문가집들이', url: '#'},
    {title: '질문과답변', url: '#'},
    {title: 'Health', url: '#'},
    {title: 'Style', url: '#'},
    {title: 'Travel', url: '#'},
];

const mainFeaturedPost = {
    title: '내일은 더 나은 집에서 살 수 있겠죠?',
    description:
        "우리는 오늘만 사는 사람이 아니기에, 내일을 위한 집을 제공합니다.",
    image: 'https://source.unsplash.com/random',
    imageText: 'main image description',
    linkText: '누르지마세요..',
};

const featuredPosts = [
    {
        title: '상품1',
        date: 'Nov 12',
        description:
            '이건 짱짱 상품입니다.',
        image: 'https://source.unsplash.com/random',
        imageLabel: 'Image Text',
    },
    {
        title: '상품2',
        date: 'Nov 11',
        description:
            '이건 덜 짱짱 상품입니다.',
        image: 'https://source.unsplash.com/random',
        imageLabel: 'Image Text',
    },
];

// const posts = [post1, post2, post3];

const sidebar = {
    title: 'About',
    description:
        '김우진과 장군우는 개발꿈나무들입니다. 열심히 개발을 하고 있어요.',
    archives: [
        {title: 'March 2020', url: '#'},
        {title: 'February 2020', url: '#'},
        {title: 'January 2020', url: '#'},
        {title: 'November 1999', url: '#'},
        {title: 'October 1999', url: '#'},
        {title: 'September 1999', url: '#'},
        {title: 'August 1999', url: '#'},
        {title: 'July 1999', url: '#'},
        {title: 'June 1999', url: '#'},
        {title: 'May 1999', url: '#'},
        {title: 'April 1999', url: '#'},
    ],
    social: [
        {name: 'GitHub', icon: GitHubIcon},
        {name: 'Twitter', icon: TwitterIcon},
        {name: 'Facebook', icon: FacebookIcon},
    ],
};

const theme = createTheme();

export default function Blog() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Container maxWidth="lg">
                {/*최상단*/}
                <Header title="내일의 집" sections={sections}/>
                <main>
                    <MainFeaturedPost post={mainFeaturedPost}/>
                    <Grid container spacing={4}>
                        {featuredPosts.map((post) => (
                            <FeaturedPost key={post.title} post={post}/>
                        ))}
                    </Grid>
                    <Grid container spacing={5} sx={{mt: 3}}>
                        {/*<Main title="From the firehose" posts={posts} />*/}
                        <Main title="상품쫘라락"/>
                        <Sidebar
                            title={sidebar.title}
                            description={sidebar.description}
                            archives={sidebar.archives}
                            social={sidebar.social}
                        />
                    </Grid>
                </main>
            </Container>
            <Footer
                title="내일의 집"
                description="특정 어플리케이션과 관련이 없음을 알립니다."
            />
        </ThemeProvider>
    );
}