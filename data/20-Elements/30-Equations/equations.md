```
title: Equations
tag:
 - markdown
```

It is possible to write equations using this tools. To do this, you need to use the LaTeX syntax or the [ASCIIMath syntax].

## Libraries used

### ASCIIMath
[Markdown-it-math]: This library allows to write equations using the light [ASCIIMath syntax]. Since this syntaxe is light and simple, the library renders quickly equations. However, the library is limited and does not allow to write complex equations.
+ `_$` and `$_` define an inline text equation.
+ `_$$` and `$$_` define a block equation.
```md
The determinant _$Delta$_ is:
_$$
Delta = b^2 - 4*a*c
$$_
Second-order polynome solutions are :
_$$x_{1,2} = (-b +- sqrt{b^2 - 4 * a * c})/(2*a)$$_
```

The determinant _$Delta$_ is:
_$$
Delta = b^2 - 4*a*c 
$$_
Second-order polynome solutions are :
_$$x_{1,2} = (-b +- sqrt{b^2 - 4 * a * c})/(2*a)$$_



### LaTeX
[MathJax]: To overcomes limitations in mathematical equations, MathJax is embedded. These libraries allow to write equations using the complet LaTeX syntax.

**Know Issue:** MathJax is an heavy library (36MB). This is due to the large possibilities and the large font choose. *If you do not need MathJax, feel free to remove it from the "js" file.*


+ `$` and `$` define an inline text equation.
+ `$$` and `$$` define a block equation.

```md
The determinant $\Delta$ is:
$$
\Delta = b^2 - 4 \cdot a \cdot c
$$
Second-order polynome solutions are :
$$x_(1.2) = frac{-b +- \sqrt{b^2 - 4 \cdot a \cdot c}}{2 \cdot a}$$
```

The determinant $\Delta$ is:
$$
\Delta = b^2 - 4 \cdot a \cdot c
$$
Second-order polynome solutions are :
$$x_{1.2} = \frac{-b \pm \sqrt{b^2 - 4 \cdot a \cdot c}}{2 \cdot a}$$

**Source:**
+ [Markdown-it]
+ [Markdown-it-math]
+ [ASCIIMath Syntax]
+ [MathJax]

[Markdown-it]: https://github.com/markdown-it/markdown-it
[Markdown-it-math]: https://github.com/runarberg/markdown-it-math
[MathJax]: https://www.mathjax.org/

[ASCIIMath Syntax]: https://runarberg.github.io/ascii2mathml/
