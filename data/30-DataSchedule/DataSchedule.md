```
title: Data Structure
toc: 2
```

It is possible to define tasks and address them an order.

To do so, it is necessary to define the previous element of note using the list:

```
previous:
 - previous Element1's id
 - previous Element2's id
```

The tool will populate the previous and the next element list in the final document based this order.

## Example

The next note are organized as following

```mermaid
graph LR
EL1(Element 1)
EL21(Element 21)
EL22(Element 22)
EL3(Element3)

EL1 --> EL21
EL1 --> EL22
EL21 --> EL3
EL22 --> EL3
```

