<script setup>
import { ref } from 'vue';

const dropdownItems = ref([
    { name: 'Option 1', code: 'Option 1' },
    { name: 'Option 2', code: 'Option 2' },
    { name: 'Option 3', code: 'Option 3' }
]);

const dropdownSpaces = ref([
    { name: 'Less than 30 square metres', code: 'less20m' },
    { name: 'Between 30 and 60 square metres', code: 'less60m' },
    { name: 'More than 60 square metres', code: 'more60m' }
]);

const dropdownAvailabilities = ref([
    { name: 'Less than 1 h / day', code: 'less1h' },
    { name: 'Between 1 and 3 h / day', code: 'less3h' },
    { name: 'More than 3 h / day', code: 'more3h' }
]);

const dropdowndangers = ref([
    { name: 'I do not care about the breed', code: 'dangerous' },
    { name: 'I do not want a dangerous breed', code: 'notdangerous' }
]);

// const dropdownItem = ref(null);
const dropdownSpace = ref(null);
const radioGarden = ref(null);
const dropdownAvailability = ref(null);
const dropdowndangerous = ref(null);
</script>

<template>
    <div class="col-12 md:col-6">
            <div className="card">
                <h5>Preference form</h5>
                <p>Please fill the following form so we can evaluate which dogs can match with your availability.</p>
            </div>
            <div class="card p-fluid">
                <!-- <h5>Horizontal</h5>
                <div class="field grid">
                    <label for="name3" class="col-12 mb-2 md:col-2 md:mb-0">Name</label>
                    <div class="col-12 md:col-10">
                        <InputText id="name3" type="text" />
                    </div>
                </div>
                <div class="field grid">
                    <label for="email3" class="col-12 mb-2 md:col-2 md:mb-0">Email</label>
                    <div class="col-12 md:col-10">
                        <InputText id="email3" type="text" />
                    </div>
                </div> -->
                <!-- <div class="field grid">
                    <label for="state" class="col-12 mb-2 md:col-2 md:mb-0">State</label>
                    <div class="col-12 md:col-10">
                        <Dropdown id="state" v-model="dropdownItem" :options="dropdownItems" optionLabel="name" placeholder="Select One"></Dropdown>
                    </div>
                </div> -->
                <div class="field grid">
                    <label for="space" class="col-12 mb-2 md:col-2 md:mb-0">Space</label>
                    <div class="col-12 md:col-10">
                        <Dropdown id="space" v-model="dropdownSpace" :options="dropdownSpaces" optionLabel="name" placeholder="Select One"></Dropdown>
                    </div>
                </div>
                <div class="grid">
                    <div class="col-12 md:col-5">
                        <div class="field-radiobutton mb-0">
                            <RadioButton id="option1" name="garden" value="withgarden" v-model="radioGarden" />
                            <label for="option1">With garden</label>
                        </div>
                    </div>
                    <div class="col-12 md:col-5">
                        <div class="field-radiobutton mb-0">
                            <RadioButton id="option2" name="garden" value="withoutgarden" v-model="radioGarden" />
                            <label for="option2">Without garden</label>
                        </div>
                    </div>
                </div>
                <div class="field grid">
                    <label for="availability" class="col-12 mb-2 md:col-2 md:mb-0">Availability</label>
                    <div class="col-12 md:col-10">
                        <Dropdown id="availability" v-model="dropdownAvailability" :options="dropdownAvailabilities" optionLabel="name" placeholder="Select One"></Dropdown>
                    </div>
                </div>
                <div class="field grid">
                    <label for="dangerous" class="col-12 mb-2 md:col-2 md:mb-0">Dangerous</label>
                    <div class="col-12 md:col-10">
                        <Dropdown id="dangerous" v-model="dropdowndangerous" :options="dropdowndangers" optionLabel="name" placeholder="Select One"></Dropdown>
                    </div>
                </div>
                <div class="card">
                    <span class="p-buttonset">
                        <Button label="Send" icon="pi pi-check" />
                        &nbsp;
                        <Button label="Cancel" icon="pi pi-times" />
                    </span>
                </div>
                <div class="col-12 mt-5 text-center">
                    <i class="pi pi-fw pi-arrow-left text-blue-500 mr-2" style="vertical-align: center"></i>
                    <router-link to="/landing" class="text-blue-500">Go to landing page</router-link>
                </div>
            </div>
    </div>
    
</template>
