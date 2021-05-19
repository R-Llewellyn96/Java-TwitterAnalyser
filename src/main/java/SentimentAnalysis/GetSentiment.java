package SentimentAnalysis;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class GetSentiment {

    /**
     * This method uses Stanford's Natural Language Processing library to perform sentiment analysis on text,
     * returning a score between 0 and 4, where 0 = very negative and 4 = very positive
     * @param tweetText Text to be analysed and given sentiment score
     * @return tweetSentiment The sentiment score of the passed text, a value between 0 and 4,
     * if the ML failed this will be 5 and should be checked by the method caller
     */
   public static int getSentimentScore(String tweetText) {

       // Tweet sentiment set to 5, maximum sentiment by NLP is 4, so if 5 is returned the ML failed
       int tweetSentiment = 5;

       // Check passed text is not empty
       if (tweetText != null && tweetText.length() > 0) {

           // Set properties of the Natural Language Processing Pipeline
           Properties properties = new Properties();
           properties.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");

           // Initialise pipeline for running stanford NLP
           StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

           // Integer checks length of sentence
           int longest = 0;

           // Split test into sentences to perform NLP processing
           Annotation annotation = pipeline.process(tweetText);

           // For each sentence in the passed text, split it into words in a tree,
           // evaluate the sentiment of each word, then give a score for the entire tree
           for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {

               // Create a tree based on the words in the sentence, used in bag of words ML model
               Tree treeOfWords = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);

               // Sentiment score between 0 - 4, 0 = very negative, 4 = very positive
               int sentiment = RNNCoreAnnotations.getPredictedClass(treeOfWords);

               // If the sentence is the longest in the passed document, then update sentiment value,
               // so we get the sentiment of the longest sentence in a block of text.
               if (sentence.toString().length() > longest) {
                   tweetSentiment = sentiment;
                   longest = sentence.toString().length();
               }
           }
       }
       // Return the sentiment score for the longest part of the tweet
       return tweetSentiment;
   }
}
