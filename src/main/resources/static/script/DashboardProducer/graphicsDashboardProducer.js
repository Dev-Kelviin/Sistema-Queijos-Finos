const certificationsAchieveds = document.getElementById('graphicCertificationsAchieveds');

new Chart(certificationsAchieveds, {
type: 'bar',

data: {
  labels: ['Não Possui', 'Sim/POA', 'SUSAF', 'SISBI', 'SELO ARTE', 'SIF'], //avaliar com professor a barra não possui
  datasets: [{
      maxBarThickness: 100,
      label: ' ', // remover aparência da label
      data: [55, 77, 30, 25, 25, 3],
      backgroundColor: ['rgba(75, 192, 192, 0.6)'],
      borderColor: ['rgba(75, 192, 192, 10)'],
      borderWidth: 4
  }]
},

options: {

  scales: {

    y: {
        ticks: {
            font: {
                family: 'Poppins', // Alterar aqui a família da fonte
                size: 16 // Alterar aqui o tamanho da fonte
            }
        },
        grid: {
            display: true, // ativa as linhas de grade do eixo Y
            lineWidth: 1
        },
        beginAtZero: true
    },

    x: {
        ticks: {
            font: {
                family: 'Poppins', // Alterar aqui a família da fonte
                size: 16 // Alterar aqui o tamanho da fonte
            }
        },
        grid: {
            display: true, // ativa as linhas de grade do eixo X
            lineWidth: 1
        }
    }
},

beginAtZero: true

}
});


