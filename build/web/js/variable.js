var maxhistory = 10;
var maxpe = 4;
var maxlab = 4;
var selectedhistory = 0;
var selectedpe = 0;
var selectedlab = 0;
var selectedhistorylist = [];
var selectedpelist = [];
var selectedlablist = [];
var ddxcolor = ['#DE7A22','#6AB187','#F52549','#FFD64D','#283655'];
var diagnoses =['']
var finaldiagnosis;
var history1 = [];

var wordstemplate = [
                {text: "", weight: 12.2},
                {text: "", weight: 7.8},
                {text: "", weight: 4.7},
                {text: "appendicitis", weight: 2.7},
                {text: "gastroenteritis", weight: 2.4},
                {text: "urinary tract infection", weight: 2.3},
                {text: "ectopic pregnancy", weight: 2.1},
                {text: "pelvic inflammatory disease", weight: 2.0},
                {text: "appendicitis", weight: 1.9},
                {text: "gastroenteritis", weight: 1.7},
                {text: "urinary tract infection", weight: 1.4},
                {text: "ectopic pregnancy", weight: 1.2},
                {text: "pelvic inflammatory disease", weight: 1.0}
                /* ... */
              ];
var finalwordstemplate = [
  {text: "Appendicitis", weight: 44.2},
  {text: "", weight: 14.8},
  {text: "", weight: 13.9},
  {text: "", weight: 13.1},
  {text: "", weight: 12.6},
  {text: "", weight: 11.7},
  {text: "", weight: 10.3},
  {text: "", weight: 7.6},
  {text: "", weight: 3.2},
  {text: "", weight: 2.8},
  {text: "", weight: 1.3},
  {text: "", weight: 0.7},
  {text: "", weight: 0.1},
  {text: "", weight: 7.4},
  {text: "", weight: 6.9},
  {text: "", weight: 6.5},
  {text: "", weight: 5.2},
  {text: "", weight: 4.7},
  {text: "", weight: 4.2},
  {text: "", weight: 3.1},
  {text: "", weight: 2.2},
  {text: "", weight: 1.0},
  {text: "", weight: 3.1},
  {text: "", weight: 2.2},
  {text: "", weight: 1.0}
  /* ... */
];

//var yearlevel = '';