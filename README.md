# Search Engine
 Advance Computing Concept final project

##### A simple search engine which is based on the frequency of the key words in the downloaded text files. It is developed in Java.

## Group number 
### 33
## Group Member:
### Jobanpreet Singh 110024321
### Shreya Wadhwa 110016593
### Aashka Prajapat110017889
### Mehak Taluja 110013132
### Lovejot Singh 110013573

## Project Components:
•	Imported Packages : Text Processing, Languages
•	Java File: Crawler.java 
•	Java File: Spider.java 
•	Java File: EditDistance.java 
•	Java File: MainMenu.java 
•	Java File: SearchImage.java
•	Java File: SearchQuery.java
•	Java File: SpellCheck.java 
•	Java File: Tokenizer.java
•	JSoup Libary

## Concepts Used:
1.	HTML to Text Conversion
2.	Edit Distance
3.	Hash Maps
4.	Sorting
5.	Tokenizer
6.	Regular Expression

## Flow of Execution of the Search Engine:
1.	Use web crawler to crawl the web and recursively retrieve URLs of JavaTPoint and W3Schools.
2.	Each URL is parsed to a text file using JSoup.
3.	String is converted to token using Java String Tokenizer.
4.	All URLs are indexed into a Hash Map.
5.	Spell Checker is implemented using Edit Distance to provide suggestion in case of misspelled words.
6.	Regular Expression is used to search the text files generated and search string
7.	To implement page ranking, frequency of these words along with the URL index are stored in the Hash Map.
8.	The page ranking Hash Map is sorted in decreasing order of frequency words.

## Screenshots

![image](https://user-images.githubusercontent.com/32582640/78415658-19e18680-75f1-11ea-8468-6940421794d1.png)

![image](https://user-images.githubusercontent.com/32582640/78415688-5a410480-75f1-11ea-9ad7-762c8c72d1b3.png)

![image](https://user-images.githubusercontent.com/32582640/78415700-75ac0f80-75f1-11ea-8307-11f47ad05814.png)



