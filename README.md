# Java-TwitterAnalyser
This program uses Twitter's API to gather Tweets and performs Sentiment Analysis, <br />
using Stanford's Natural Language Processing library, <br />
and a targeted keyword search for different topics. <br /> <br />
NOTE: You must have a Twitter Developer account, for this app to work! <br />
!!! Place 3 files: consumer_key.txt, consumer_secret.txt, bearer_token.txt <br /> 
each with their corresponding API keys on the first line, inside the "access_codes" folder, <br />
or this app will not work, as the Twitter API requires user authentication. <br /> <br />
How to use: enter a CLI argument as the twitter handle of the user you wish to scrape e.g. @BBCNews <br />
<br />
What do you get with this program?<br />
<br />  
- [x] Functionality for targeting an individual Twitter user to gather upto 3200 tweets.
- [x] Analysis of tweets using Stanford's NLP library to rank sentiment of each tweet between 0 and 4, 0 = very negative, 4 = very positive.
- [x] Analysis of tweets to search for keywords and classifying tweets as Political, Profane or Racist.
- [x] Addition of new keywords is possible by adding words to wordlist text files.
- [x] Generation of Comma Separated Values (CSV) files, containing classified tweets.
- [x] Parallelised NLP, provides upto 3X+ performance improvement on quad core processors vs serial execution.
<!-- -->
<br />
Future Updates:<br />
<br />

- [ ] Ability to gather only negative, neutral or positive sentiment tweets
- [ ] Return JSON objects instead of CSV (Functionality is there but not implemented)
- [ ] Addition of new Wordlist classifications
- [ ] Spring Server integration (in active development)
<br />
<!-- end of the list -->
