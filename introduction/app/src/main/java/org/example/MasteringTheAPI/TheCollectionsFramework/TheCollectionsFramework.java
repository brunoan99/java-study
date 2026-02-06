package org.example.MasteringTheAPI.TheCollectionsFramework;

import org.example.Utils.Article;

public class TheCollectionsFramework implements Article {
  public void execute() {
    Article.display("Storing Elements in a Collection", new StoringElementsInACollection());

    Article.display("Iterating over the Elements of a Collection", new IteratingOverTheElementsOfACollection());

    Article.display("Extending Collection with List", new ExtendingCollectionWithList());

    Article.display("Extending Collection with Set, SortedSet and NavigableSet",
        new ExtendingCollectionWithSetSortedSetAndNavigableSet());

    Article.display("Creating and Processing Data with the Collections Factory Methods",
        new CreatingAndProcessingDataWithTheCollectionsFactoryMethods());

    Article.display("Storing Elements in Stacks and Queues",
        new StoringElementsInStacksAndQueues());

    Article.display("Using Maps to Store Key Value Pairs",
        new UsingMapsToStoreKeyValuePairs());

    Article.display("Managing the Content of a Map",
        new ManagingTheContentOfAMap());

    Article.display("Handling Map Values with Lambda Expressions",
        new HandlingMapValuesWithLambdaExpressions());

    Article.display("Keeping Keys Sorted with SortedMap and NavigableMap",
        new KeepingKeysSortedWithSortedMapAndNavigableMap());

    Article.display("Choosing Immutable Types for Your Key",
        new ChoosingImmutableTypesForYourKey());

    Article.display("Choosing the Right Implementation Between ArrayList and LinkedList",
        new ChoosingTheRightImplementationBetweenArrayListAndLinkedList());
  }
}

class CollectionHierarchy {
  /* @formatter:off */
  // Collection hierarchy
  //                  Iterable
  //                     |
  //            |-> Collection <-|
  //            |                |
  //          List              Set
  //                             |
  //                         SortedSet
  //                             |
  //                        NavigableSet
  /* @formatter:on */
}
