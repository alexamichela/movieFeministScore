# How Feminist Are These Movies: an Intersectional Metric to Analyzing Gender Imbalances in Movies
M. Ausbrooks, K. Cao, A. Halim
Fall 2022 – CS 230
December 16, 2022
## Introduction
Hollywood’s portrayal of women has historically been notorious for relaying and enforcing 
harmful sexist stereotypes and misogynistic archetypes. Given the breadth of media we consume today, it
is important for us to evaluate and analyze how women are depicted in films, as accurate representation in
media really does matter. Movies nowadays should consist of characters that are depicted to reflect the
diversity of humanity. Currently, there are multiple tests that seek to evaluate “how feminist a movie is”;
among them, the Bechdel-Wallace Test most notably judges whether a movie is feminist by asking two
questions: Does it have at least two named female characters? And do those featured characters have at
least one conversation not about a man? Other tests such as the Villareal, Ko, and White test expand on
the Bechdel test by including the movie crew, gender, and race into the matrix. Although these tests reveal
anti-feminist aspects of a film, they fail to provide an intersectional evaluation of women in film that
encompass, race, sexuality, and disability.
\nTo create a more all-encompassing feminist score – including race, sexuality, and gender, we
incorporated three tests in our self-defined feminist score. The three tests we used are: Villareal (index 4),
Ko (index 6), Rees Davies (index 12). The Villareal test examines female protagonists, evaluating
whether or not they are portrayed as one of three common stereotypes (sexualized, expressionless, or
matriarch) and if they later subvert this characterization; for example if they are sexual or choose a sexual
identity of their own. The Ko Test evaluates the racial diversity of movies, checking if a movie features a
non-white, female-identifying person who speaks in five or more scenes and in English. The Rees-Davies
Test evaluates the gender diversity across the departments working on a movie. A movie passes the
Rees-Davies test if every department has two or more women. Thus, combining these specific tests
provide an intersectional lens to evaluate how feminist a movie is, as together, they analyze racial
diversity amoung the cast and the characters, acknowledge LBGTQ+ representation.
## Method
As explained previously, our feminist score of a 0 to 3 is calculated by examining how a movie
performs in the Villareal, Ko, and Rees-Davies tests. Before a movie is able to receive a feminist score, it
must set its test results from the input file and populate its instance variable vector so that the feminist
score can be calculated from the three previously mentioned tests. In the Movie class, any attempt to print
out the string representation of a movie that hasn’t had its test results populated will print a line telling the
user to set test results. In the Movie Collection class, when movies are added to the collection from the
input file, their respective test results are also populated. A movie’s feminist score is determined in the
following manner:
\n● If the movie passes none of the tests, it gets a score of 0.
\n● If the movie passes only one of the tests, it gets a score of 1.
\n● If the movie passes any two of the tests, it gets a score of 2.
\n● If the movie passes all three tests, it gets a score of 3.
\nOur program allows users to easily sort a movie collection by feminist scores through an
implementation of a PriorityQueue data structure that is based on a LinkedMaxHeap, which implements
the MaxHeap interface. A MaxHeap is an extension of a BinaryTree, in which each node’s element
priority is greater than or equal to both of its children’s. Thus, the highest priority element is at the root.
This prioritization is based on the movie’s feminist score, with higher-scoring movies receiving higher
priority. In order to break ties in priority, movies with the same feminist score are ranked
lexicographically by their movie title. The decision to break ties lexicographically was motivated by a
desire to facilitate understanding of our feminist score and the subsequent ranking of movies by it.
Additionally, the ways in which we would have liked to further evaluate a movie’s diversity and
representation, such as the inclusion of BIPOC and LGBTQ+ individuals in all departments, were not
easily available to us through the other tests and data.
\nThus, when users want to sort a movie collection by feminist score, they enqueue each movie to a
priority queue, in which elements are prioritized by their feminist score, and dequeue all of the movies to
show the ranked list of movies from most feminist to least. If more movies were added to our data, our
solution to sorting a movie collection by feminist score would not have to change. The addition of more
movies would only result in more ties in feminist scores that would get resolved by our method for
breaking ties lexicographically by movie title. The only problem we foresee would be if the movies of the
same title and with the same feminist score were added because they would have the same priority. Thus,
we would have to break ties in another way, maybe adding an additional tie-breaker by the size of the
crew, so that smaller movies get a lower priority.
## Conclusions
### Findings:
Ranking movie collection from most feminist to least:
\nMovie title: Bad Moms Actors: 16 Feminist Score: 2
Movie title: Batman v Superman: Dawn of Justice Actors: 122 Feminist Score: 2
Movie title: Fantastic Beasts and Where to Find Them Actors: 61 Feminist Score: 2
Movie title: Finding Dory Actors: 28 Feminist Score: 2
Movie title: Ghostbusters Actors: 54 Feminist Score: 2
Movie title: Hacksaw Ridge Actors: 90 Feminist Score: 2
Movie title: Hidden Figures Actors: 96 Feminist Score: 2
Movie title: Ice Age: Collision Course Actors: 18 Feminist Score: 2
Movie title: Independence Day: Resurgence Actors: 28 Feminist Score: 2
Movie title: Kung Fu Panda 3 Actors: 36 Feminist Score: 2
Movie title: Now You See Me 2 Actors: 48 Feminist Score: 2
Movie title: Pete's Dragon Actors: 41 Feminist Score: 2
Movie title: Sausage Party Actors: 33 Feminist Score: 2
Movie title: Sing Actors: 55 Feminist Score: 2
Movie title: Star Trek Beyond Actors: 23 Feminist Score: 2
Movie title: Storks Actors: 25 Feminist Score: 2
Movie title: Suicide Squad Actors: 54 Feminist Score: 2
Movie title: The Angry Birds Movie Actors: 47 Feminist Score: 2
Movie title: The Divergent Series: Allegiant Actors: 91 Feminist Score: 2
Movie title: The Purge: Election Year Actors: 41 Feminist Score: 2
Movie title: 10 Cloverfield Lane Actors: 10 Feminist Score: 1
Movie title: Alice Through the Looking Glass Actors: 23 Feminist Score: 1
Movie title: Arrival Actors: 60 Feminist Score: 1
Movie title: Boo! A Madea Halloween Actors: 42 Feminist Score: 1
Movie title: Captain America: Civil War Actors: 26 Feminist Score: 1
Movie title: Central Intelligence Actors: 25 Feminist Score: 1
Movie title: Doctor Strange Actors: 29 Feminist Score: 1
Movie title: Jason Bourne Actors: 43 Feminist Score: 1
Movie title: La La Land Actors: 49 Feminist Score: 1
Movie title: Miss Peregrine's Home for Peculiar Children Actors: 53 Feminist Score: 1
Movie title: Moana Actors: 7 Feminist Score: 1
Movie title: Passengers Actors: 27 Feminist Score: 1
Movie title: Ride Along 2 Actors: 50 Feminist Score: 1
Movie title: Sully Actors: 61 Feminist Score: 1
Movie title: Teenage Mutant Ninja Turtles: Out of the Shadows Actors: 23 Feminist Score: 1
Movie title: The Accountant Actors: 58 Feminist Score: 1
Movie title: The Conjuring 2: The Enfield Poltergeist Actors: 32 Feminist Score: 1
Movie title: The Girl on the Train Actors: 20 Feminist Score: 1
Movie title: The Jungle Book Actors: 11 Feminist Score: 1
Movie title: The Legend of Tarzan Actors: 58 Feminist Score: 1
Movie title: The Magnificent Seven Actors: 48 Feminist Score: 1
Movie title: X-Men: Apocalypse Actors: 90 Feminist Score: 1
Movie title: Zootopia Actors: 32 Feminist Score: 1
Movie title: Deadpool Actors: 34 Feminist Score: 0
Movie title: Don't Breathe Actors: 10 Feminist Score: 0
Movie title: Lights Out Actors: 14 Feminist Score: 0
Movie title: Rogue One: A Star Wars Story Actors: 33 Feminist Score: 0
Movie title: The Boss Actors: 37 Feminist Score: 0
Movie title: The Secret Life of Pets Actors: 31 Feminist Score: 0
Movie title: Trolls Actors: 32 Feminist Score: 0
Upon evaluating the scores of the movies,it was unsurprising that no movies in this dataset
received a perfect score on our test. Due to the pervasive misogynistic nature of film, our expectations for
movies that pass our feminist score were low. We picked our three specific tests to define our feminist
score due to their ability to better evaluate whether or not a movie is feminist and diverse; from our
results, it’s clear that these movies do not value intersectionality or portray sophisticated, diverse
characters. A trend derived from our results that was surprising to us was how the majority of movies with
a score of 0 were children’s movies or cartoons. Since kids’ movies tend to generalize and play into
stereotypes for entertainment, it is a sad reality that the implications of this are dangerous and pose a
threat to children’s self-esteem. These results were disappointing to us, as the media children consume
during their developmental stages influence their mannerisms, affect their mental health, and shape their
beliefs. Having such pervasive media that enforces harmful stereotypes, racial biases, and lack of
representation of LGBTQ+ individuals encourages, and often leads to the internalization of said
stereotypes.
Additionally, we focused in on a couple movies that had thought provoking feminist scores: Trolls
(Feminist Score: 0), Sausage Party (Feminist Score: 1), and Moana (Feminist Score: 3). The Trolls movie
is one marketed at children, but especially young girls. The protagonist is a spunky troll and heir to the
troll equivalent of a throne. We would have assumed a movie focused on a female protagonist would at
least have a cast of female characters supporting them on and off screen; thus passing the Rees Davies
Test. Furthermore, considering this jukebox musical comedy animated film brought together such a
star-studded ensemble, it is disappointing that it failed the Ko test to significantly feature a non-white,
female-identifying person. We would have expected Moana, another movie focusing on a female
protagonist and one celebrated for its representation of Polynesian myths, to also be supported by a team
of women behind the scenes. Unfortunately, Moana doesn’t pass Reese Davies. The last movie that was
incredibly surprising to us was Sausage Party that had a score of 2. It failed the Villarreal Test, but passed
the other two. We did not expect an adult comedy film to receive such a high feminist score because of its
silly subject matter.
Although the combination of tests that we used to define our feminist score provides a more
intersectional way to evaluate movies, very few of these tests take into account LGBTQ+ and disability
visibility in films. Given the lack of tests that examine the way queer and disabled characters are
portrayed in movies, we would add a variation of the Vito-Russo test
(https://www.glaad.org/sri/2018/vitorusso) to the existing thirteen tests.
The Vito-Russo test takes into account the representation of queer characters and scores the
movie based on how these characters are portrayed in the film. Given the intersectionality of race, gender,
and sexuality, we want to develop a test that encompasses and reflects how individuals are portrayed in
real life – not as historically inaccurate stereotypes that the film industry has indoctrinated.
## Collaboration:
Overall, we divided the work for this project fairly between each groupmate. Although finding
time to meet in person was challenging at times, we were able to utilize Zoom to work on the project
together and enabled the remote control function on Zoom so that each group mate had a chance to be the
“driver”. In addition, to hold each other accountable, we created a group chat to talk about the final
project and update each other on the progress we made alone. When we met together to code, we applied
the pair programming techniques refined in lab and made sure each person had an equal opportunity to be
the “driver”. Furthermore, we attended office hours with the CS230 professors and TA’s together to
ensure that we all understood the solutions to the problems we ran into and discussed them afterwards as a
group to clarify any misunderstandings. In terms of norms, we were all cognizant of the norms we
identified in lab and made sure to explain concepts to each other as we coded. Additionally, a key part of
teamwork is effective communication and listening. We consistently took the time to hear out each
member of the team when we had ideas, doubts, or questions. Before making large decisions, we’d
consult each other and make sure we were all in agreement. Working on this project together was a
rewarding experience, as we were able to learn from each other, explore concepts learned in class
collaboratively, and define a feminist score that reflects the diverse backgrounds and experiences of our
group.
