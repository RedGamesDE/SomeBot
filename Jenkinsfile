pipeline {
    agent any

    stages {
        stage("Deploy docker image") {
            when {
                buildingTag()
            }

            steps {
                withGradle {
                    withCredentials([usernamePassword(
                        credentialsId: 'slimenexus-repo-slime',
                        usernameVariable: 'SLIMENEXUS_REGISTRY_USERNAME',
                        passwordVariable: 'SLIMENEXUS_REGISTRY_PASSWORD'
                    )]) {
                        gradlew("dockerPushImage")
                    }
                }
            }
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}
