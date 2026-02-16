// ---------- Page layout ----------
#set page(
  paper: "a4",
  margin: (
    left: 1.2cm,
    right: 1.2cm,
    top: 1.5cm,
    bottom: 1.5cm
  )
)

// ---------- Fonts ----------
#set text(
  font: "Inter",
  size: 11pt
)

// Set monospace font for all raw/code text
#show raw: set text(
  font: "JetBrains Mono"
)

// ---------- Section formatting ----------
// Each section (heading level 1) starts on a new page
// Matches LaTeX \clearpage + minimal spacing
#show heading.where(level: 1): it => {
  pagebreak(weak: true)
  v(0pt)  // No space above (matches 0pt in LaTeX)
  set text(size: 17pt, weight: "regular")  // \normalfont\Large
  block(above: 0pt, below: 12pt)[#it]
}

// Level 2 headings (for "Program Output" sections)
#show heading.where(level: 2): it => {
  set text(size: 14pt, weight: "bold")
  block(above: 0.8em, below: 0.4em)[#it]
}

// ---------- Code blocks ----------
// This matches the mdframed + lstlisting setup from LaTeX
#show raw.where(block: true): it => {
  block(
    fill: rgb("#000000"),           // backgroundcolor=black
    stroke: 0.5pt + rgb("#ffffff"), // linecolor=white, linewidth=0.5pt
    width: 100%,
    inset: (                        // inner margins
      left: 0.5em,
      right: 0.5em,
      top: 0.5em,
      bottom: 0.5em
    ),
    radius: 0pt,                    // No rounded corners
    breakable: true                 // Allow breaks across pages
  )[
    #set text(
      fill: rgb("#ffffff"),         // basicstyle color=white
      size: 10pt                    // basicstyle small
    )
    #set par(leading: 0.65em)       // Line spacing in code blocks
    #it
  ]
}

// Inline code styling (not in the LaTeX but useful)
#show raw.where(block: false): it => {
  box(
    fill: rgb("#f0f0f0"),
    inset: (x: 0.2em, y: 0.1em),
    outset: (y: 0.15em),
    radius: 2pt
  )[
    #set text(
      size: 0.9em,
      fill: rgb("#000000")
    )
    #it
  ]
}

// ---------- Paragraph spacing ----------
// Matches \setlength{\parskip}{6pt}
#set par(spacing: 6pt)
#set block(spacing: 6pt)

// ---------- Lists ----------
#set list(indent: 1em, body-indent: 0.5em)
#set enum(indent: 1em, body-indent: 0.5em)

// ---------- Document content ----------
// This is where Pandoc injects the converted markdown
$body$
