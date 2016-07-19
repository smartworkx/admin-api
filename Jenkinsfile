#!groovy

node {
    // Mark the code checkout 'stage'....
    stage 'Stage Checkout'

    // Checkout code from repository and update any submodules
    checkout scm
   // sh 'git submodule update --init'

    stage 'Stage Build'

    //build your gradle flavor, passes the current build number as a parameter to gradle
    sh "gradle clean assemble"

    stage 'Cucumber'
    ansiblePlaybook(
            playbook: 'provisioning/db-servers.yml',
            inventory: 'provisioning/ci.inventory ',
            extras: '-u pi',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
    sh "gradle cucumberInt -PjvmArgs='-Dspring.profiles.active=ci'"

    stage 'Stage Archive'
    step([$class: 'ArtifactArchiver', artifacts: '**/build/libs/*.jar', fingerprint: true])
    sh 'echo Publishing doc'
}

//stage 'Deploy test'
//input 'Do you want to install on test?'

//node {
//    sh 'echo deploying on test'
//    sh 'ansible-playbook -i provisioning/test.inventory provisioning/db-servers.yml'
//    sh 'ansible-playbook -i provisioning/test.inventory provisioning/api-servers.yml'
//}

stage 'Deploy prod'
input 'Do you want to install on prod?'

node {
    sh 'echo deploying on prod'
    ansiblePlaybook(
            playbook: 'provisioning/api-servers.yml',
            inventory: 'provisioning/prod.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
}
