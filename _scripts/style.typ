// ---------- Page layout ----------
// ---------- Variables from Pandoc ----------
#let startPage = $start-page$
#let endPage = $end-page$

// Set the counter to startPage
#counter(page).update(startPage)

#set page(
  paper: "a4",
  margin: (left: 1.2cm, right: 1.2cm, top: 1.5cm, bottom: 1.5cm),
  
  // Header logic to hide content if past endPage
  foreground: context {
    let current = counter(page).at(here()).first()
    let limit = if str(endPage) != "" { int(endPage) } else { 99999 }
    
    if current > limit {
      // Create an opaque white box over the entire page 
      // to effectively "cut off" the document
      place(top + left, rect(width: 100%, height: 100%, fill: white))
    }
  },

footer: context {
    let current = counter(page).at(here()).first()
    // Convert endPage to an integer for comparison and display
    let limit = if str(endPage) != "" { int(endPage) } else { 0 }
    
    // Only show footer if we are within the allowed range
    if limit == 0 or current <= limit {
      set align(right)
      set text(8pt)
      
      // If a limit is set, show "Page X of LIMIT", otherwise just "Page X"
      if limit != 0 {
        [Page #current of #limit]
      } else {
        [Page #current]
      }
    }
  }
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
  set text(size: 17pt, weight: "bold")  // \normalfont\Large
  block(above: 0pt, below: 12pt)[#it]
}

// Level 2 headings (for "Program Output" sections)
#show heading.where(level: 2): it => {
  set text(size: 14pt, weight: "regular")
  block(above: 0.8em, below: 0.6em)[#it]
}

// ---------- Code blocks ----------
// This matches the mdframed + lstlisting setup from LaTeX
#show raw.where(block: true): it => {
  block(
    fill: rgb("#1e1f22"),           // Claude.icls TEXT background
    stroke: 0.5pt + rgb("#393b40"), // Claude.icls DOC_CODE_BLOCK effect color
    width: 100%,
    inset: (                        // inner margins
      left: 1em,
      right: 1em,
      top: 1em,
      bottom: 1em
    ),
    radius: 2pt,                    // No rounded corners
    breakable: true                 // Allow breaks across pages
  )[
    #set text(
      fill: rgb("#bcbec4"),         // Claude.icls TEXT foreground
      size: 10pt                    // basicstyle small
    )
    #set par(leading: 0.65em)       // Line spacing in code blocks
    #it
  ]
}

// Inline code styling
#show raw.where(block: false): it => {
  box(
    fill: rgb("#343539"),           // Claude.icls DOC_CODE_INLINE background
    inset: (x: 0.2em, y: 0.1em),
    outset: (y: 0.15em),
    radius: 2pt
  )[
    #set text(
      size: 0.9em,
      fill: rgb("#ced0d6")          // Claude.icls DOC_CODE_INLINE foreground
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