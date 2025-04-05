package com.Blog_Application.CoreNlp;


import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class NlpConfig {
    
    public StanfordCoreNLP setupNLP() {
        Properties props = new Properties();
        
        // Adding required annotators: tokenize, sentence split, POS, lemma, parse, sentiment
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");

        // Setting the path for the POS tagger model
        String modelPath = "models/english-left3words-distsim.tagger";
        props.setProperty("pos.model", modelPath);
        
        // Initialize the StanfordCoreNLP object with the properties
        return new StanfordCoreNLP(props);

    }
}
