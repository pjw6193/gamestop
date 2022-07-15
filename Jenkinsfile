pipeline {
    agent any
    tools {
        maven 'Maven' 
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("pjw6193/gamestop:${TAG}")
                }
            }
        }
	    stage('Pushing Docker Image to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("pjw6193/gamestop:${TAG}").push()
                        docker.image("pjw6193/gamestop:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy'){
            steps {
                sh "docker stop gamestop | true"
                sh "docker rm gamestop | true"
                sh "docker run --name gamestop -d -p 9595:9595 pjw6193/gamestop:${TAG}"
            }
        }
    }
}