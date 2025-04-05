package com.Blog_Application.CoreNlp;

import java.util.Arrays;
import java.util.List;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Commentmoder {

    private List<String> badWords = Arrays.asList("idiot", "stupid", "hate");

    public String moderateComment(String comment) {

        NlpConfig nlpConfig = new NlpConfig();
        StanfordCoreNLP pipeline = nlpConfig.setupNLP();

        CoreDocument doc = new CoreDocument(comment);
        pipeline.annotate(doc);

        // Perform sentiment analysis
        String sentiment = extractSentiment(doc);
        System.out.println("Sentiment: " + sentiment);

        if (sentiment.equals("Negative") || containsBadWords(comment)) {
            return "toxic Comment:Flagged or moderation";
        }

        return "The comment is fine";
    }

    public Boolean containsBadWords(String comment) {
        for (String word : badWords) {
            if (comment.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String extractSentiment(CoreDocument doc){
        for(CoreSentence sentence:doc.sentences()){
            String sentiment=sentence.sentiment();
            if(sentiment != null){
                return sentiment;
            }
        }
        return "Neutral";
    }

}
