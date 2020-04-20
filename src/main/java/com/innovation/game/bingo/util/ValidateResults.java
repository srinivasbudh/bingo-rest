package com.innovation.game.bingo.util;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValidateResults {
   public static List<Integer> forUser(List<Integer> userToken, List<Integer> resultsArray, String resultType){

       List<Integer> subset = new ArrayList<>();
       switch (resultType) {
           case "COLUMN-B":for (int i=0;i<5;i++){
               subset.add(userToken.get(i));
                }
               break;
           case "COLUMN-I":
               for (int i=5;i<10;i++){
                   subset.add(userToken.get(i));
               }
               break;
           case "COLUMN-G":
               for (int i=15;i<20;i++){
                   subset.add(userToken.get(i));
               }
               break;
           case "COLUMN-O":
               for (int i=20;i<25;i++){
                   subset.add(userToken.get(i));
               }
               break;
           case "FULLHOUSE":
               subset = userToken;
               break;
           default:
               List<Integer> duplicateList = Arrays.asList(99999);
               return duplicateList;

       }


       Collection<Integer> similar = new HashSet<Integer>( resultsArray  );
       Collection<Integer> different = new HashSet<Integer>();
       different.addAll( resultsArray );
       different.addAll( subset );

       similar.retainAll( similar );
       different.removeAll( resultsArray );
       different.remove(0);

       System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n", subset, resultsArray, similar, different);
       System.out.println("End Result"+different.isEmpty());
       return different.stream().map(it -> it).collect(Collectors.toList());
   }
}
