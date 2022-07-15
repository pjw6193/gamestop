pipeline {
    agent any
    tools {
        maven 'Maven' 
    }
    environment {
        TAG = "${BUILD_NUMBER}"
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
                    docker.withRegistry('https://registry.hub.docker.com', '9eab68a3-d07c-4b70-ab61-3c60961290f7') {
                        docker.image("pjw6193/gamestop:${TAG}").push()
                        docker.image("pjw6193/gamestop:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Run local'){
            steps {
                sh "docker stop gamestop | true"
                sh "docker rm gamestop | true"
                sh "docker run --name gamestop -d -p 9595:9595 pjw6193/gamestop:${TAG}"
            }
        }
    }
}