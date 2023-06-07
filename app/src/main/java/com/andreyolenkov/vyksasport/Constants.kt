package com.andreyolenkov.vyksasport

import com.andreyolenkov.vyksasport.db.repository.ComplexRepository
import com.andreyolenkov.vyksasport.db.repository.EventsRepository
import com.andreyolenkov.vyksasport.db.repository.GroupsRepository
import com.andreyolenkov.vyksasport.db.repository.PersonsRepository
import com.andreyolenkov.vyksasport.db.repository.SectionsRepository

lateinit var APP:MainActivity
lateinit var REPOSITORY_COMPLEX:ComplexRepository
lateinit var REPOSITORY_EVENTS:EventsRepository
lateinit var REPOSITORY_SECTIONS: SectionsRepository
lateinit var REPOSITORY_GROUPS: GroupsRepository
lateinit var REPOSITORY_PERSONS: PersonsRepository
