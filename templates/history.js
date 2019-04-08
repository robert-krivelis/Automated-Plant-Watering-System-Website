//JSONDATA

var historyCanvas = document.getElementById("historyChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var Data = {
  labels: {{plantobj.xvalues | tojson }},
  datasets: [{
    label: "Moisture %",
    fill: false,
    data: {{plantobj.ymoisture | tojson }},
    lineTension: 0.5,
    backgroundColor: 'transparent',
    //backgroundColor:'rgba(0,0,255,0.5)',
    borderColor: 'green',
    borderWidth: 3,
   
    usePointStyle: false,
    pointBorderColor: 'green',
    pointBackgroundColor: 'green',
    pointRadius: 3,
    pointHoverRadius: 10,
    pointHitRadius: 30,
    pointBorderWidth: 2,
    pointStyle: 'circle'
  },
  {
    label: "Temperature", 
    fill: false,
    data: {{plantobj.ytemperature | tojson }},
    lineTension: 0.5,
    backgroundColor: 'transparent',
    //backgroundColor:'rgba(0,0,255,0.5)',
    borderColor: 'red',
    borderWidth: 3,
   
    usePointStyle: false,
    pointBorderColor: 'red',
    pointBackgroundColor: 'red',
    pointRadius: 3,
    pointHoverRadius: 10,
    pointHitRadius: 30,
    pointBorderWidth: 2,
    pointStyle: 'circle'
  },
  {
    label: "Humidity", 
    fill: false,
    data: {{plantobj.yhumidity | tojson }},
    lineTension: 0.5,
    backgroundColor: 'transparent',
    //backgroundColor:'rgba(0,0,255,0.5)',
    borderColor: 'blue',
    borderWidth: 3,
   
    usePointStyle: false,
    pointBorderColor: 'blue',
    pointBackgroundColor: 'blue',
    pointRadius: 3,
    pointHoverRadius: 10,
    pointHitRadius: 30,
    pointBorderWidth: 2,
    pointStyle: 'circle'
  }]
};

var chartOptions = {
  legend: {
    display: true,
    position: 'top',
    labels: {
      boxWidth: 30,
      fontColor: 'black'
    }
  },
  scales: {
    yAxes: [{
      display: true,
      gridLines: {
                color: "rgba(0, 0, 0, 0)",
            },
      ticks: {
        suggestedMin: cm,    // minimum will be 0, unless there is a lower value.
        // OR //
        suggestedMax: cm ,
        beginAtZero: false   // minimum value will be 0.
      }
    }],
    xAxes: [{
      display: true,
      gridLines: {
                color: "rgba(0, 0, 0, 0)",
            },
      ticks: {
        suggestedMin: 5,    // minimum will be 0, unless there is a lower value.
        beginAtZero: false   // minimum value will be 0.
        
      }
    }]
  },
  
  title: {
    display:false,
    fontColor: 'black',
    text: "Current Dampness of the Soil",
    position: 'top'
  }
};

var lineChart = new Chart(historyCanvas, {
  type: 'line',
  data: Data,
  options: chartOptions
});
