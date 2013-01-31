# S-System
## A ClojureScript L-system implementation with Turtle Graphics

## Overview

My goal is to create the most comprehensive client-side L-system implementation available, without sacrificing API clarity. I also intend for the system to be genuinely interactive, explorable, and self-documenting. This will take some time.

The current version is a hastily-assembled port of Scott Lowe's [l-system-fun](http://www.scottlowe.eu/blog/2012/03/04/l-system-axial-trees-with-clojure/) project. I've written a tiny subset of the requisite 2D drawing code, but intend to make a faithful port of Quil to ClojureScript. It should be relatively straightforward since Processing.js is a pretty faithful recreation of its JVM sister. I may even be able to fork and dalap-ify Quil. We'll see. Another possibility is sticking with the Canvas API and implementing not-quite-quil.

## Obvious Next Steps

1. Support for general context-sensitive (k, l)-systems
2. Support for Stochastic L-systems
3. Parametric L-systems
4. 3D turtle graphics: I can start with a simple wraper atop three.js and branch off if I get any kooky ideas.

## Concerns

1. Performance, performance, performance. Doing more than 5 or so recursive steps on some of the 0L tree systems already consumes a ton of computation time. I'm sure this can be improved drastically by being careful about what parts of ClojureScript I use. This will require continual profiling, and will likely involve delegating bits of the project out to hand-written JavaScript with potential for macrology fun. Memoization will likely prove crucial for guaranteeing responsive time-scrubbing through a system.

## Learnability/Explorability

I intend to make Bret proud. The system will be self documenting and will include within itself an interactive tutorial to the world of L-systems. I intend to build an L-system editor tool with powerful constructs for examining how a system evolves over time. It will also be valuable to display many productions side-by-side for Stochastic systems to get a feel for the variety. Tasks like tweaking angles or adjusting colors should be completely painless, and *everything* in the API should be visually manipulable/explorable. Getting an intuition for how replacement rules correspond to geometric structure is crucial. I have not succeeded unless I've built an environment where *discovery* is a natural consequence of playing with the system.

If the timing works out, this whole system might find itself wrapped up inside of Light Table as an editing mode. *An IDE for plants* :P

## Inspiration/Reference

Tons of people on the internet are fascinated by L-systems. It's easy to see why: you can produce incredibly organic, aesthetically appealing results with just a few hours work. That said, there are hundreds, maybe thousands of reference L-system implementations of varying degrees of completeness. I should take heed of and respect these works and draw inspiration from them when appropriate. Below I'll maintain a list of all implementations and writings that have (directly or indirectly) influenced the design of this system:

### Implementations


### Writings


## Random Thoughts

I've read about using L-systems to procedurally generate music. It would be incredible to be able to simultaneusly compose a piece of music while drawing a picture. I not very music-theory literate, but this would be the perfect opportunity to wet my feet (and possible fall off the deep end).

Could L-systems be applied to other media? I know they're related to cellular automata in some respects. Could an L-system somehow be manifest as an AI / an active agent by assigning behaviors to different characters in a production?

I'm sure someone has toyed with this idea, but what if you *evolved* an L-system? Randomly mutate the production rules themselves (this is different from Stochastic L-systems!) each generation, and have some sort of metric for fitness, e.g. height, branching factor, flower yield, etc.

This idea touches on something that's been brewing in my mind for a while. Can you teach a computer what beauty is? Or, posed another way, can you use machine-learning to determine what our human minds (in the aggregrate) discern as beauty? Can visual beauty be quantified? Implementation would be the same as the above paragraph, but the fitness condition would be human preference.

With sufficient realism (raytracing on a server), maybe we could teach a computer how to render, say, flowers, or entire plants, starting from very simple axioms. Wouldn't that be something?

That's enough waxing philosophic for now.


## Timeline

This is one of my greatest weaknesses. I'm horrible at estimating how long things take. I have to have *something* ready in time for the Spark class, which falls on March 16th. Maybe I can convince someone to let me do parts of this as my Senior project next fall. And if I manage to bring a computer with me on the bike trip this summer, then I'll be hacking on this before bed. We'll see...


## Contributions

I welcome contributions, criticism, and advice of any kind. Feel free to [email me](mailto:ethanis@mit.edu).
